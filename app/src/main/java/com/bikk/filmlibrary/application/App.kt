package com.bikk.filmlibrary.application

import android.app.Application
import com.bikk.filmlibrary.di.DI.mainModule
import com.bikk.filmlibrary.di.DI.retrofitBuilderModule
import com.bikk.filmlibrary.di.DI.viewModelsModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin() {
            androidContext(applicationContext)
            modules(
                listOf(
                    retrofitBuilderModule,
                    mainModule,
                    viewModelsModules
                )
            )
        }
    }
}