package com.bikk.filmlibrary.data.retrofit

import com.bikk.filmlibrary.models.movies.MoviesModel
import com.bikk.filmlibrary.models.actors.ActorsModels
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {
    @GET("3/movie/popular?api_key=3fe888e646ae612e98d77c0b7a08da79&language=en-US&page=1")
    suspend fun getPopularMovie(@Query("page") page: Int): Response<MoviesModel>

    @GET("3/movie/{id}/credits?api_key=3fe888e646ae612e98d77c0b7a08da79&language=en-US")
    suspend fun getActorsMovie(@Path("id") Int: Int): Response<ActorsModels>
}

