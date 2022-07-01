package com.bikk.filmlibrary.models.movies

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bikk.filmlibrary.util.Const
import java.io.Serializable

@Entity(tableName = Const.MOVIE_TABLE)
data class MovieItemModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "overview")
    val overview: String,
    @ColumnInfo(name = "poster_path")
    val poster_path: String,
    @ColumnInfo(name = "release_date")
    val release_date: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "vote_average")
    val vote_average: Double,
) : Serializable