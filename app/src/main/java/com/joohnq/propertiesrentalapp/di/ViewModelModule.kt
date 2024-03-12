package com.joohnq.propertiesrentalapp.di

import com.joohnq.propertiesrentalapp.model.repository.FirebaseRepository
import com.joohnq.propertiesrentalapp.viewmodel.AuthViewModel
import com.joohnq.propertiesrentalapp.viewmodel.UserViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ViewModelModule {
    @Provides
    fun provideUserViewModel(
        firebaseRepository: FirebaseRepository
    ): UserViewModel =
        UserViewModel(
            firebaseRepository = firebaseRepository,
        )

    @Provides
    fun provideAuthViewModel(
        firebaseRepository: FirebaseRepository,
        userViewModel: UserViewModel
    ): AuthViewModel =
        AuthViewModel(firebaseRepository = firebaseRepository, userViewModel = userViewModel)
}