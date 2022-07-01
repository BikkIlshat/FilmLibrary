package com.bikk.filmlibrary.di.modules.local

import com.bikk.filmlibrary.models.movies.MovieItemModel
import kotlinx.coroutines.flow.Flow


interface RoomModuleInt {

    val allMovies: Flow<List<MovieItemModel>>
    suspend fun insertMovies(movieItemModel: MovieItemModel)
    suspend fun deleteMovies(movieItemModel: MovieItemModel)


}