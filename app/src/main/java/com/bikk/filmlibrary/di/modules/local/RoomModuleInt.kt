package com.bikk.filmlibrary.di.modules.local

import androidx.lifecycle.LiveData
import com.bikk.filmlibrary.models.MovieItemModel
import kotlinx.coroutines.flow.Flow


interface RoomModuleInt {

    val allMovies: Flow<List<MovieItemModel>>
    suspend fun insertMovies(movieItemModel: MovieItemModel)
    suspend fun deleteMovies(movieItemModel: MovieItemModel)


}