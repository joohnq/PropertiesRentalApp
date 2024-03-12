package com.joohnq.propertiesrentalapp.di

import android.content.Context
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.joohnq.propertiesrentalapp.google.GoogleAuthUiClient
import com.joohnq.propertiesrentalapp.model.repository.FirebaseRepository
import com.joohnq.propertiesrentalapp.model.repository.FirebaseRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DependenceInjectionModule {

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    fun provideSignInClient(@ApplicationContext context: Context): SignInClient =
        Identity.getSignInClient(context)

    @Provides
    fun provideFirebaseRepository(
        ioScope: CoroutineScope,
        db: FirebaseFirestore,
        auth: FirebaseAuth
    ): FirebaseRepository {
        return FirebaseRepositoryImpl(
            scope = ioScope,
            db = db,
            auth = auth
        )
    }

    @Provides
    @Singleton
    fun provideCoroutineScope(): CoroutineScope {
        return CoroutineScope(SupervisorJob() + Dispatchers.IO)
    }

    @Provides
    fun provideGoogleAuthUiClient(
        @ApplicationContext context: Context,
        signInClient: SignInClient,
        auth: FirebaseAuth
    ): GoogleAuthUiClient = GoogleAuthUiClient(context, signInClient, auth)
}



