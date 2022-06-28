package com.bikk.filmlibrary.screens.details

import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bikk.filmlibrary.R
import com.bikk.filmlibrary.databinding.FragmentDetailsBinding
import com.bikk.filmlibrary.models.MovieItemModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val viewBinding: FragmentDetailsBinding by viewBinding()
    lateinit var currentMovie: MovieItemModel
    private val viewModel: DetailsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        currentMovie = arguments?.getSerializable("movie") as MovieItemModel
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        context?.let {
            Glide.with(it)
                .setDefaultRequestOptions(
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image)
                )
                .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2${currentMovie.poster_path}")
                .centerCrop()
                .into(viewBinding.imgDetail)
        }
        with(viewBinding) {
            tvTitle.text = currentMovie.title
            tvDate.text = currentMovie.release_date
            tvDescription.text = currentMovie.overview
            tvDescription.justificationMode = JUSTIFICATION_MODE_INTER_WORD
        }

    }


}