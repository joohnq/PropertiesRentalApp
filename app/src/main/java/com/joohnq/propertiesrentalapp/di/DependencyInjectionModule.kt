package com.joohnq.propertiesrentalapp.di

import android.content.Context
import android.location.Geocoder
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.joohnq.propertiesrentalapp.google.GoogleAuthUiClient
import com.joohnq.propertiesrentalapp.model.repository.FirebaseRepository
import com.joohnq.propertiesrentalapp.model.repository.FirebaseRepositoryImpl
import com.joohnq.propertiesrentalapp.model.repository.LocationRepository
import com.joohnq.propertiesrentalapp.model.repository.LocationRepositoryImpl
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
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideSignInClient(@ApplicationContext context: Context): SignInClient =
        Identity.getSignInClient(context)

    @Provides
    @Singleton
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
    @Singleton
    fun provideGoogleAuthUiClient(
        @ApplicationContext context: Context,
        signInClient: SignInClient,
        auth: FirebaseAuth
    ): GoogleAuthUiClient = GoogleAuthUiClient(context, signInClient, auth)

    @Provides
    @Singleton
    fun provideLocationRepository(
        geocoder: Geocoder,
        @ApplicationContext context: Context,
    ): LocationRepository {
        return LocationRepositoryImpl(
            geocoder = geocoder,
            context = context
        )
    }

    @Provides
    @Singleton
    fun provideGeocoder(
        @ApplicationContext context: Context
    ): Geocoder {
        return Geocoder(context)
    }
}



