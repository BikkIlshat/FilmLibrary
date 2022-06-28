package com.bikk.filmlibrary.di.modules

import com.bikk.filmlibrary.data.retrofit.ApiService
import com.bikk.filmlibrary.models.MoviesModel
import retrofit2.Response

class RemoteModule(private val apiService: ApiService) : RemoteModuleInt {
    override suspend fun getMovies(): Response<MoviesModel> {
        return apiService.getPopularMovie()
    }
}