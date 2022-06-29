package com.bikk.filmlibrary.di.modules.remote

import com.bikk.filmlibrary.data.retrofit.ApiService
import com.bikk.filmlibrary.models.MoviesModel
import com.bikk.filmlibrary.models.actors.ActorsModels
import retrofit2.Response

class RemoteModule(private val apiService: ApiService) : RemoteModuleInt {
    override suspend fun getMovies(): Response<MoviesModel> {
        return apiService.getPopularMovie()
    }
    override suspend fun getActorsMovie(id: Int): Response<ActorsModels> {
        return apiService.getActorsMovie(id)
    }
}