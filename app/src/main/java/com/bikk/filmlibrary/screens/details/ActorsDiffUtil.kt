package com.bikk.filmlibrary.screens.details

import androidx.recyclerview.widget.DiffUtil
import com.bikk.filmlibrary.models.MovieItemModel
import com.bikk.filmlibrary.models.actors.Cast

class ActorsDiffUtil : DiffUtil.ItemCallback<Cast>() {

    override fun areItemsTheSame(oldItem: Cast, newItem: Cast) =
        oldItem === newItem

    override fun areContentsTheSame(oldItem: Cast, newItem: Cast) =
        oldItem == newItem




}