package com.bikk.filmlibrary.screens.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bikk.filmlibrary.R
import com.bikk.filmlibrary.databinding.ItemLayoutBinding
import com.bikk.filmlibrary.models.MovieItemModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import by.kirich1409.viewbindingdelegate.viewBinding

class MoviesListAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<MovieItemModel, MoviesListAdapter.MoviesViewHolder>(MoviesDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder =
        MoviesViewHolder (
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_layout, parent, false)
        )


    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener { onClickListener.onClick(item) }
        holder.bind(item)
    }

    class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val viewBinding: ItemLayoutBinding by viewBinding()
        fun bind(listMovies: MovieItemModel) = with(viewBinding) {
            itemTitle.text = listMovies.title
            itemDate.text = listMovies.release_date
            verticalImdb.text = listMovies.vote_average.toString()
            itemImg.apply {
                Glide
                    .with(context)
                    .setDefaultRequestOptions(
                        RequestOptions()
                            .placeholder(R.drawable.loading_animation)
                            .error(R.drawable.ic_broken_image)
                    )
                    .load(listMovies.poster_path)
                    .centerCrop()
                    .into(this)
            }
        }
    }
}