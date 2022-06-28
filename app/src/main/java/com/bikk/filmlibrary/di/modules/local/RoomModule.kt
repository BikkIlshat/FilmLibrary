package com.bikk.filmlibrary.di.modules.local

import com.bikk.filmlibrary.application.App
import com.bikk.filmlibrary.models.MovieItemModel

class RoomModule: RoomModuleInt {
    override suspend fun insertMovies(movieItemModel: MovieItemModel) {
        App.instance.databaseService.getMovieDao().insert(movieItemModel)
    }

    override suspend fun deleteMovies(movieItemModel: MovieItemModel) {
        App.instance.databaseService.getMovieDao().delete(movieItemModel)
    }
}