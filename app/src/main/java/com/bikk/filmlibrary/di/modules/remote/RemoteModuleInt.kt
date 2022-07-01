package com.bikk.filmlibrary.di.modules.remote

import com.bikk.filmlibrary.models.movies.MoviesModel
import com.bikk.filmlibrary.models.actors.ActorsModels
import retrofit2.Response

interface RemoteModuleInt {
    suspend fun getMovies(page:Int): Response<MoviesModel>
    suspend fun getActorsMovie(id: Int): Response<ActorsModels>
}