package com.joohnq.propertiesrentalapp.model.repository

import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.joohnq.propertiesrentalapp.model.entities.User
import com.joohnq.propertiesrentalapp.util.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

interface FirebaseRepository {
    suspend fun createUserWithEmailAndPassword(
        email: String, password: String, onFailure: (String) -> Unit
    ): Boolean

    suspend fun signInWithEmailAndPassword(
        email: String, password: String, onFailure: (String) -> Unit
    ): Boolean

    suspend fun authUserWithGoogle(
        user: User, onFailure: (String) -> Unit
    ): Boolean

    suspend fun currentUser(): String?

    suspend fun logout(): Boolean

    suspend fun createUserOnDatabase(
        user: User, onFailure: (String) -> Unit
    ): Boolean

    suspend fun getUserFromDatabase(onFailure: (String) -> Unit): User?

    suspend fun checkIfExistOnDatabase(user: User, onFailure: (String) -> Unit): Boolean
}

class FirebaseRepositoryImpl @Inject constructor(
    private val scope: CoroutineScope,
    private val db: FirebaseFirestore,
    private val auth: FirebaseAuth,
) : FirebaseRepository {
    override suspend fun createUserWithEmailAndPassword(
        email: String, password: String, onFailure: (String) -> Unit
    ): Boolean = suspendCoroutine { continuation ->
        try {
            auth
                .createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener { authResult ->
                    scope.launch {
                        val userNotNull = authResult.user != null
                        val user = User(
                            id = authResult.user?.uid,
                            email = email,
                        )

                        if (!userNotNull) {
                            onFailure("User not created!")
                        }

                        createUserOnDatabase(
                            user,
                            onFailure
                        )
                        continuation.resume(userNotNull)
                    }
                }.addOnFailureListener { error ->
                    when (error) {
                        is FirebaseAuthInvalidCredentialsException -> {
                            onFailure("Invalid credentials! ${error.message}")
                        }

                        is FirebaseAuthEmailException -> {
                            onFailure("Email invalid! ${error.message}")
                        }

                        else -> {
                            onFailure("Operation failed")
                        }
                    }
                }
        } catch (e: FirebaseAuthException) {
            onFailure("Operation failed! ${e.message}")
        }
    }

    override suspend fun signInWithEmailAndPassword(
        email: String, password: String, onFailure: (String) -> Unit
    ): Boolean = suspendCoroutine { continuation ->
        try {
            auth
                .signInWithEmailAndPassword(email, password)
                .addOnSuccessListener { authResult ->
                    val userNotNull = authResult.user != null

                    continuation.resume(userNotNull)
                }.addOnFailureListener { error ->
                    when (error) {
                        is FirebaseAuthInvalidUserException -> {
                            onFailure("User not found! ${error.message}")
                        }

                        is FirebaseAuthInvalidCredentialsException -> {
                            onFailure("Invalid credentials! ${error.message}")
                        }

                        is FirebaseAuthEmailException -> {
                            onFailure("Email invalid! ${error.message}")
                        }

                        else -> {
                            onFailure("Operation failed")
                        }
                    }
                }
        } catch (e: FirebaseAuthException) {
            onFailure("Operation failed")
        }
    }

    override suspend fun authUserWithGoogle(
        user: User, onFailure: (String) -> Unit
    ): Boolean = suspendCoroutine { continuation ->
        scope.launch {
            val exists = checkIfExistOnDatabase(user, onFailure)
            if (!exists) {
                val created: Boolean = createUserOnDatabase(user, onFailure)
                if (!created) onFailure("User not created!")
                continuation.resume(created)
            } else {
                continuation.resume(true)
            }
        }
    }


    override suspend fun currentUser(): String? = suspendCoroutine { continuation ->
        continuation.resume(auth.currentUser?.uid)
    }

    override suspend fun logout(): Boolean = suspendCoroutine { continuation ->
        auth.signOut()
        continuation.resume(true)
    }

    override suspend fun createUserOnDatabase(
        user: User, onFailure: (String) -> Unit
    ): Boolean = suspendCoroutine { continuation ->
        try {
            user.id?.run {
                db.collection(Constants.FIREBASE_USERS).document(user.id).set(user)
                    .addOnSuccessListener {
                        continuation.resume(true)
                    }.addOnFailureListener { error ->
                        onFailure(error.message ?: "Operation failed")
                    }
            } ?: onFailure("Id is null")
        } catch (e: FirebaseException) {
            onFailure("Operation failed")
        }
    }

    override suspend fun getUserFromDatabase(onFailure: (String) -> Unit): User? =
        suspendCoroutine { continuation ->
            try {
                scope.launch {
                    val id = currentUser()
                    id?.run {
                        val document =
                            db.collection(Constants.FIREBASE_USERS).document(id).get().await()

                        if (document.exists()) {
                            val user = document.toObject<User>()
                            continuation.resume(user)
                        } else {
                            onFailure("User not found")
                        }
                    } ?: onFailure("User is null")
                }
            } catch (e: FirebaseException) {
                onFailure(e.message ?: "Operation failed")
            }
        }

    override suspend fun checkIfExistOnDatabase(user: User, onFailure: (String) -> Unit): Boolean =
        withContext(Dispatchers.IO) {
            suspendCoroutine { continuation ->
                user.id?.let { id ->
                    try {
                        db.collection(Constants.FIREBASE_USERS).document(id).get()
                            .addOnCompleteListener { task ->
                                val document: DocumentSnapshot = task.result
                                if (task.isSuccessful) continuation.resume(document.exists())
                                else continuation.resume(false)
                            }
                    } catch (e: FirebaseException) {
                        onFailure("Operation failed")
                    }
                } ?: onFailure("User Id is null")
            }
        }
}