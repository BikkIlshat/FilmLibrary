package com.bikk.filmlibrary.screens.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.bikk.filmlibrary.R
import com.bikk.filmlibrary.databinding.ItemLayoutBinding
import com.bikk.filmlibrary.models.MovieItemModel

class MoviesPagingAdapter(private val onClickListener: OnClickListener) :
    PagingDataAdapter<MovieItemModel, MoviesViewHolder>(MoviesDiffUtilCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder =
        MoviesViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_layout, parent, false)
        )


    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val item = getItem(position)

        holder.itemView.setOnClickListener { item?.let { it1 -> onClickListener.onClick(it1) } }
        item?.let { holder.bind(it) }

    }
}

class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val viewBinding by viewBinding(ItemLayoutBinding::bind)

    fun bind(listMovies: MovieItemModel?) = with(viewBinding) {
        itemTitle.text = listMovies?.title
        itemDate.text = listMovies?.release_date
        verticalImdb.text = listMovies?.vote_average.toString()
        itemImgMovie.load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2${listMovies?.poster_path}")
    }
}

private object MoviesDiffUtilCallback : DiffUtil.ItemCallback<MovieItemModel>() {

    override fun areItemsTheSame(oldItem: MovieItemModel, newItem: MovieItemModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieItemModel, newItem: MovieItemModel) =
        oldItem.title == newItem.title
                && oldItem.release_date == newItem.release_date
                && oldItem.vote_average == newItem.vote_average
                && oldItem.poster_path == newItem.poster_path

}


