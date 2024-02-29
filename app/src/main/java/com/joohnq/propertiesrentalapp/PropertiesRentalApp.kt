package com.joohnq.propertiesrentalapp

import android.app.Application
import com.joohnq.propertiesrentalapp.di.appModule
import com.joohnq.propertiesrentalapp.di.repositoryModule
import com.joohnq.propertiesrentalapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class PropertyRentalApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@PropertyRentalApp)
            module {
                listOf(
                    appModule,
                    viewModelModule,
                    repositoryModule
                )
            }
        }
    }
}