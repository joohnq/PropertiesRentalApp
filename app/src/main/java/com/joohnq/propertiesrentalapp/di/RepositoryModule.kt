package com.joohnq.propertiesrentalapp.di

import android.content.Context
import android.location.Geocoder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.joohnq.propertiesrentalapp.model.repository.DatabaseRepository
import com.joohnq.propertiesrentalapp.model.repository.DatabaseRepositoryImpl
import com.joohnq.propertiesrentalapp.model.repository.FirebaseRepository
import com.joohnq.propertiesrentalapp.model.repository.FirebaseRepositoryImpl
import com.joohnq.propertiesrentalapp.model.repository.LocationRepository
import com.joohnq.propertiesrentalapp.model.repository.LocationRepositoryImpl
import com.joohnq.propertiesrentalapp.model.repository.PropertyRepository
import com.joohnq.propertiesrentalapp.model.repository.PropertyRepositoryImpl
import com.joohnq.propertiesrentalapp.model.services.PropertyDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideLocationRepository(
        geocoder: Geocoder,
        @ApplicationContext context: Context,
    ): LocationRepository = LocationRepositoryImpl(
        geocoder = geocoder,
        context = context
    )

    @Provides
    @Singleton
    fun provideDatabaseRepository(
    ): DatabaseRepository = DatabaseRepositoryImpl()

    @Provides
    @Singleton
    fun provideFirebaseRepository(
        scope: CoroutineScope,
        db: FirebaseFirestore,
        auth: FirebaseAuth,
    ): FirebaseRepository = FirebaseRepositoryImpl(
        scope = scope,
        db = db,
        auth = auth
    )

    @Provides
    @Singleton
    fun providePropertyRepository(
        scope: CoroutineScope,
        propertyDataSource: PropertyDataSource
    ): PropertyRepository = PropertyRepositoryImpl(
        scope = scope,
        propertyDataSource = propertyDataSource
    )

}