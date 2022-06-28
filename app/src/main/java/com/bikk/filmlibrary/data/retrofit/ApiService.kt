package com.bikk.filmlibrary.data.retrofit

import com.bikk.filmlibrary.models.MoviesModel
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {
    @GET("3/movie/popular?api_key=3fe888e646ae612e98d77c0b7a08da79&language=en-US&page=1")
    suspend fun getPopularMovie(): Response<MoviesModel>
}