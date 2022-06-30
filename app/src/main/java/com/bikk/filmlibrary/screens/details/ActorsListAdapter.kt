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
import com.bikk.filmlibrary.databinding.ItemLayoutBinding
import com.bikk.filmlibrary.databinding.ItemLayoutMovieActorsBinding
import com.bikk.filmlibrary.models.MovieItemModel
import com.bikk.filmlibrary.models.actors.Cast
import com.bikk.filmlibrary.screens.main.adapter.MoviesDiffUtil
import com.bikk.filmlibrary.screens.main.adapter.MoviesListAdapter
import com.bikk.filmlibrary.screens.main.adapter.OnClickListener
import com.bikk.filmlibrary.util.Const
import com.bikk.filmlibrary.util.Const.BASE_IMAGE_URL

class ActorsListAdapter(private val onClickListener: com.bikk.filmlibrary.screens.details.OnClickListener) :
    ListAdapter<Cast, ActorsListAdapter.ActorsViewHolder>(ActorsDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorsViewHolder =
        ActorsViewHolder (
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_layout_movie_actors, parent, false)
        )


    override fun onBindViewHolder(holder: ActorsViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener { onClickListener.onClick(item) }
        holder.bind(item)
    }

    class ActorsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val viewBinding: ItemLayoutMovieActorsBinding by viewBinding()
        fun bind(cast: Cast) = with(viewBinding) {
            itemNameActor.text = cast.name
            itemImgMovieActor.apply {
                Glide
                    .with(context)
                    .setDefaultRequestOptions(
                        RequestOptions()
                            .placeholder(R.drawable.loading_animation)
                            .error(R.drawable.ic_broken_image)
                    )
                    .load("${BASE_IMAGE_URL}${ cast.profile_path }")
                    .thumbnail(01f)
                    .fitCenter()
                    .into(this)
            }
        }
    }
}