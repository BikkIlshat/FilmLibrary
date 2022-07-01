package com.bikk.filmlibrary.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bikk.filmlibrary.util.Const
import com.bikk.filmlibrary.data.room.MoviesDao
import com.bikk.filmlibrary.models.movies.MovieItemModel


@Database(
    entities = [
        MovieItemModel::class,
    ],
    version = 1,
    exportSchema = false
)

abstract class Database : RoomDatabase() {
    abstract fun getMovieDao(): MoviesDao


    companion object {
        @Volatile
        private var INSTANCE: com.bikk.filmlibrary.data.Database? = null

        fun createDatabase(context: Context): com.bikk.filmlibrary.data.Database =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                com.bikk.filmlibrary.data.Database::class.java,
                Const.DATABASE_NAME
            ).build()
    }
}