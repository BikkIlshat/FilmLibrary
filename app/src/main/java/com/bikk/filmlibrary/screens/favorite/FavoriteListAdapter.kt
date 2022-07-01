package com.bikk.filmlibrary.screens.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bikk.filmlibrary.R
import com.bikk.filmlibrary.models.movies.MovieItemModel
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.bikk.filmlibrary.databinding.ItemLayoutBinding
import com.bikk.filmlibrary.databinding.ItemLayoutMovieActorsBinding
import com.bikk.filmlibrary.screens.main.adapter.OnClickListener
import com.bikk.filmlibrary.util.Const

class FavoriteListAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<MovieItemModel, FavoriteListAdapter.MoviesViewHolder>(FavoriteMoviesDiffUtil()) {

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
        private val viewBinding by viewBinding(ItemLayoutBinding::bind)
        fun bind(listMovies: MovieItemModel) = with(viewBinding) {
            itemTitle.text = listMovies.title
            itemDate.text = listMovies.release_date
            verticalImdb.text = listMovies.vote_average.toString()
            itemImgMovie.load("${Const.BASE_IMAGE_URL}${listMovies?.poster_path}")
        }
    }
}