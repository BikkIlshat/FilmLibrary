package com.bikk.filmlibrary.di.modules.local

import com.bikk.filmlibrary.application.App
import com.bikk.filmlibrary.models.movies.MovieItemModel
import kotlinx.coroutines.flow.Flow

class RoomModule() : RoomModuleInt {

    override val allMovies: Flow<List<MovieItemModel>> =
        App.instance.databaseService.getMovieDao().getAllMovies()



    override suspend fun insertMovies(movieItemModel: MovieItemModel) {
        App.instance.databaseService.getMovieDao().insert(movieItemModel)
    }

    override suspend fun deleteMovies(movieItemModel: MovieItemModel) {
        App.instance.databaseService.getMovieDao().delete(movieItemModel)
    }

}