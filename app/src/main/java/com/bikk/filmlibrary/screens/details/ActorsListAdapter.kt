package com.bikk.filmlibrary.screens.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bikk.filmlibrary.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bikk.filmlibrary.databinding.ItemLayoutMovieActorsBinding
import com.bikk.filmlibrary.models.actors.Cast
import com.bikk.filmlibrary.util.Const.BASE_IMAGE_URL

class ActorsListAdapter() : ListAdapter<Cast, ActorsListAdapter.ActorsViewHolder>(ActorsDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorsViewHolder =
        ActorsViewHolder (
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_layout_movie_actors, parent, false)
        )


    override fun onBindViewHolder(holder: ActorsViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ActorsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val viewBinding: ItemLayoutMovieActorsBinding by viewBinding()
        fun bind(cast: Cast) = with(viewBinding) {
            itemNameActor.text = cast.name
            itemImgMovieActor.apply {
                Glide
                    .with(context)
                    .load("$BASE_IMAGE_URL${cast.profile_path }")
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(viewBinding.itemImgMovieActor)
            }
        }
    }
}