package com.bikk.filmlibrary.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bikk.filmlibrary.util.Const
import com.bikk.filmlibrary.models.MovieItemModel

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
   fun insert(movieItemModel: MovieItemModel)

    @Delete
    fun delete(movieItemModel: MovieItemModel)

    @Query("SELECT * from ${Const.MOVIE_TABLE}")
    fun getAllMovies(): LiveData<List<MovieItemModel>>
}