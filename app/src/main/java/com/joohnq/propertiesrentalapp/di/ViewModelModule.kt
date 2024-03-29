package com.joohnq.propertiesrentalapp.di

import com.google.android.gms.location.FusedLocationProviderClient
import com.joohnq.propertiesrentalapp.model.repository.FirebaseRepository
import com.joohnq.propertiesrentalapp.model.repository.LocationRepository
import com.joohnq.propertiesrentalapp.model.repository.PropertyRepository
import com.joohnq.propertiesrentalapp.viewmodel.AuthViewModel
import com.joohnq.propertiesrentalapp.viewmodel.LocationViewModel
import com.joohnq.propertiesrentalapp.viewmodel.MainViewModel
import com.joohnq.propertiesrentalapp.viewmodel.PermissionsViewModel
import com.joohnq.propertiesrentalapp.viewmodel.PropertyViewModel
import com.joohnq.propertiesrentalapp.viewmodel.UserViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {
    @Provides
    @ViewModelScoped
    fun provideUserViewModel(
        firebaseRepository: FirebaseRepository
    ): UserViewModel =
        UserViewModel(
            firebaseRepository = firebaseRepository,
        )

    @Provides
    @ViewModelScoped
    fun provideAuthViewModel(
        firebaseRepository: FirebaseRepository,
        userViewModel: UserViewModel
    ): AuthViewModel =
        AuthViewModel(firebaseRepository = firebaseRepository, userViewModel = userViewModel)

    @Provides
    @ViewModelScoped
    fun provideLocationViewModel(
        locationRepository: LocationRepository,
        fusedLocationClient: FusedLocationProviderClient
    ): LocationViewModel =
        LocationViewModel(
            locationRepository = locationRepository,
            fusedLocationClient = fusedLocationClient
        )

    @Provides
    @ViewModelScoped
    fun providePermissionsViewModel(
    ): PermissionsViewModel =
        PermissionsViewModel()

    @Provides
    @ViewModelScoped
    fun providePropertyViewModel(
        propertyRepository: PropertyRepository,
        locationViewModel: LocationViewModel,
        mainViewModel: MainViewModel
    ): PropertyViewModel =
        PropertyViewModel(
            propertyRepository = propertyRepository,
            locationViewModel = locationViewModel,
            mainViewModel = mainViewModel
        )

    @Provides
    @ViewModelScoped
    fun provideMainViewModel(
    ): MainViewModel =
        MainViewModel()
}