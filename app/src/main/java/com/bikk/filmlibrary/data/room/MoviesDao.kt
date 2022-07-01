package com.bikk.filmlibrary.data.room

import androidx.room.*
import com.bikk.filmlibrary.models.movies.MovieItemModel
import com.bikk.filmlibrary.util.Const.MOVIE_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
   fun insert(movieItemModel: MovieItemModel)

    @Delete
    fun delete(movieItemModel: MovieItemModel)

    @Query("SELECT * FROM $MOVIE_TABLE")
    fun getAllMovies(): Flow<List<MovieItemModel>>

}