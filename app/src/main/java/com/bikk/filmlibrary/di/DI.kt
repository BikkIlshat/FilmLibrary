package com.bikk.filmlibrary.di

import com.bikk.filmlibrary.data.retrofit.ApiService
import com.bikk.filmlibrary.di.modules.local.RoomModule
import com.bikk.filmlibrary.di.modules.local.RoomModuleInt
import com.bikk.filmlibrary.di.modules.remote.RemoteModule
import com.bikk.filmlibrary.di.modules.remote.RemoteModuleInt
import com.bikk.filmlibrary.screens.details.DetailsFragment
import com.bikk.filmlibrary.screens.details.DetailsViewModel
import com.bikk.filmlibrary.screens.favorite.FavoriteFragment
import com.bikk.filmlibrary.screens.favorite.FavoriteViewModel
import com.bikk.filmlibrary.screens.main.MainFragment
import com.bikk.filmlibrary.screens.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object DI {
    val remoteModule = module {
        single<RemoteModuleInt> { RemoteModule(apiService = get()) }
    }

    val localModule = module {
        single<RoomModuleInt> { RoomModule() }
    }

    val retrofitBuilderModule = module {
        single <ApiService>{ RetrofitBuilder().getService() }
    }

    val viewModelsModules = module {
        scope<MainFragment> {
            viewModel { MainViewModel(remoteModuleInt=  get()) }
        }

        scope<DetailsFragment> {
            viewModel {DetailsViewModel(roomModuleInt =  get(), remoteModuleInt = get()) }
        }

        scope<FavoriteFragment> {
            viewModel { FavoriteViewModel(roomModuleInt = get()) }
        }
    }


}