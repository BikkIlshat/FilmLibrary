package com.bikk.filmlibrary.di.modules.local

import com.bikk.filmlibrary.models.MovieItemModel

interface RoomModuleInt {

    suspend fun insertMovies(movieItemModel: MovieItemModel)
    suspend fun deleteMovies(movieItemModel: MovieItemModel)


}