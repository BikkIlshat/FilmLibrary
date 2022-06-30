package com.bikk.filmlibrary.screens.details

import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bikk.filmlibrary.R
import com.bikk.filmlibrary.databinding.FragmentDetailsBinding
import com.bikk.filmlibrary.models.MovieItemModel
import com.bikk.filmlibrary.util.Const
import com.bikk.filmlibrary.util.Const.FAVORITE_BTN_IS_ACTIVE
import com.bikk.filmlibrary.util.Const.FAVORITE_BTN_NOT_ACTIVE
import com.bikk.filmlibrary.util.SaveSharedImpl
import com.bikk.filmlibrary.util.SavedShared
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import org.koin.android.ext.android.getKoin
import org.koin.core.scope.Scope

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val viewBinding: FragmentDetailsBinding by viewBinding()
    private var currentMovie: MovieItemModel? = null
    private val scope: Scope = getKoin().createScope<DetailsFragment>()
    private val viewModel: DetailsViewModel = scope.get()
    private val savedShared: SavedShared = SaveSharedImpl()
    private var adapter: ActorsListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentMovie = arguments?.getSerializable("movie") as MovieItemModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        init()
        setFavorite()
    }

    private fun readActorsById(): Int {
        return currentMovie?.id ?: 0
    }

    private fun initRecyclerView() {
        adapter = ActorsListAdapter()
        viewModel.getActors(id = readActorsById())
       viewBinding.rvMovieActors.adapter = adapter
        viewModel.actors.observe(viewLifecycleOwner) {
            adapter?.submitList(it.body()!!.cast)
        }
    }


    private fun setFavorite() {
        fun updateBtnFavoriteIsNotActive() {
            viewBinding.imgIcFavorite.setImageResource(
                FAVORITE_BTN_NOT_ACTIVE
            )
        }

        fun updateBtnFavoriteIsActive() {
            viewBinding.imgIcFavorite.setImageResource(
                FAVORITE_BTN_IS_ACTIVE
            )
        }

        fun saveStateFavoriteValue(boolean: Boolean) {
            savedShared.setFavorite(requireContext(), currentMovie?.id.toString(), boolean)
        }

        var isFavorite = false
        fun updateFavoriteButton(isFavorite: Boolean, valueBool: Boolean) {
            when {
                isFavorite != valueBool -> updateBtnFavoriteIsActive()
                else -> updateBtnFavoriteIsNotActive()
            }
        }

        val valueBool = savedShared.getFavorite(requireContext(), currentMovie?.id.toString())
        updateFavoriteButton(isFavorite, valueBool)
        isFavorite = valueBool
        viewBinding.imgIcFavorite.setOnClickListener {
            isFavorite = if (!isFavorite) {
                updateBtnFavoriteIsActive()
                saveStateFavoriteValue(true)
                currentMovie?.let { it1 -> viewModel.insert(it1) }
                true
            } else {
                updateBtnFavoriteIsNotActive()
                saveStateFavoriteValue(false)
                currentMovie?.let { it1 -> viewModel.delete(it1) }
                false
            }
        }

    }

    private fun init() {
        context?.let {
            Glide.with(it)
                .setDefaultRequestOptions(
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image)
                )
                .load("${Const.BASE_IMAGE_URL}${currentMovie?.poster_path}")
                .fitCenter()
                .into(viewBinding.imgDetail)
        }
        with(viewBinding) {
            tvTitle.text = currentMovie?.title
            tvDate.text = currentMovie?.release_date
            tvDescription.text = currentMovie?.overview
            tvDescription.justificationMode = JUSTIFICATION_MODE_INTER_WORD
        }
    }


}