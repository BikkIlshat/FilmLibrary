package com.bikk.filmlibrary.application

import android.app.Application
import com.bikk.filmlibrary.data.Database
import com.bikk.filmlibrary.di.DI.localModule
import com.bikk.filmlibrary.di.DI.remoteModule
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
                    remoteModule,
                    viewModelsModules,
                    localModule
                )
            )
        }
    }

    init {
        instance = this
    }

    val databaseService: Database by lazy { Database.createDatabase(applicationContext) }


    companion object {
        lateinit var instance: App
            private set
    }
}