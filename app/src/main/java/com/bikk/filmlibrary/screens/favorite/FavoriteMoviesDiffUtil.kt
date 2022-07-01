package com.bikk.filmlibrary.screens.favorite

import androidx.recyclerview.widget.DiffUtil
import com.bikk.filmlibrary.models.movies.MovieItemModel

class FavoriteMoviesDiffUtil : DiffUtil.ItemCallback<MovieItemModel>() {

    override fun areItemsTheSame(oldItem: MovieItemModel, newItem: MovieItemModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieItemModel, newItem: MovieItemModel) =
        oldItem.title == newItem.title
                && oldItem.release_date == newItem.release_date
                && oldItem.vote_average == newItem.vote_average
                && oldItem.poster_path == newItem.poster_path
}