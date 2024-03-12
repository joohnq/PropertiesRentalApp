//package com.joohnq.propertiesrentalapp.di
//
//import com.joohnq.propertiesrentalapp.model.repository.AuthRepository
//import com.joohnq.propertiesrentalapp.model.repository.AuthRepositoryImpl
//import com.joohnq.propertiesrentalapp.model.repository.DatabaseRepository
//import com.joohnq.propertiesrentalapp.model.repository.DatabaseRepositoryImpl
//import dagger.Binds
//import dagger.Module
//import dagger.hilt.InstallIn
//import dagger.hilt.android.components.ActivityComponent
//import javax.inject.Singleton
//
//@Module
//@InstallIn(ActivityComponent::class)
//abstract class AppModule {
//
//    @Binds
//    abstract fun bindAuthRepository(
//        authRepositoryImpl: AuthRepositoryImpl
//    ): AuthRepository
//
//    @Binds
//    abstract fun bindDatabaseRepository(
//        databaseRepository: DatabaseRepositoryImpl
//    ): DatabaseRepository
//}