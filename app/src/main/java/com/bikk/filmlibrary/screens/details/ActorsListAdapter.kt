package com.bikk.filmlibrary.screens.details

import android.graphics.text.LineBreaker
import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bikk.filmlibrary.R
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import coil.size.PixelSize
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.bikk.filmlibrary.databinding.ItemLayoutBinding
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
        private val viewBinding by viewBinding(ItemLayoutMovieActorsBinding::bind)
        fun bind(cast: Cast) = with(viewBinding) {
            itemNameActor.text = cast.name
            itemNameActor.justificationMode = JUSTIFICATION_MODE_INTER_WORD
            itemImgMovieActor.load("$BASE_IMAGE_URL${cast.profile_path}") {
                transformations(RoundedCornersTransformation(25f))
            }
        }
    }
}


