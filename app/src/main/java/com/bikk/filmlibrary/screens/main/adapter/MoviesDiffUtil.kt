package com.bikk.filmlibrary.screens.main.adapter

import androidx.recyclerview.widget.DiffUtil
import com.bikk.filmlibrary.models.MovieItemModel

class MoviesDiffUtil : DiffUtil.ItemCallback<MovieItemModel>() {

    override fun areItemsTheSame(oldItem: MovieItemModel, newItem: MovieItemModel) =
        oldItem === newItem

    override fun areContentsTheSame(oldItem: MovieItemModel, newItem: MovieItemModel) =
        oldItem == newItem


}