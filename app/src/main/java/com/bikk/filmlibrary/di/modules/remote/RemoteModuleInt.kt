package com.bikk.filmlibrary.di.modules.remote

import com.bikk.filmlibrary.models.MoviesModel
import com.bikk.filmlibrary.models.actors.ActorsModels
import retrofit2.Response
import retrofit2.http.Path

interface RemoteModuleInt {
    suspend fun getMovies(page:Int): Response<MoviesModel>
    suspend fun getActorsMovie(id: Int): Response<ActorsModels>
}