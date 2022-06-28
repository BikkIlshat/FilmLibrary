package com.bikk.filmlibrary.di

import com.bikk.filmlibrary.data.retrofit.ApiService
import com.bikk.filmlibrary.di.modules.RemoteModule
import com.bikk.filmlibrary.di.modules.RemoteModuleInt
import com.bikk.filmlibrary.screens.main.MainFragment
import com.bikk.filmlibrary.screens.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object DI {
    val mainModule = module {
        single<RemoteModuleInt> { RemoteModule(apiService = get()) }
    }

    val retrofitBuilderModule = module {
        single <ApiService>{ RetrofitBuilder().getService() }
    }

    val viewModelsModules = module {
        scope<MainFragment> {
            viewModel { MainViewModel(get()) }
        }
    }
}