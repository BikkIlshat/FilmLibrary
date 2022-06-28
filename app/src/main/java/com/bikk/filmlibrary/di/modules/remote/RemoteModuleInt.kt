package com.bikk.filmlibrary.di.modules.remote

import com.bikk.filmlibrary.models.MoviesModel
import retrofit2.Response

interface RemoteModuleInt {
    suspend fun getMovies(): Response<MoviesModel>
}