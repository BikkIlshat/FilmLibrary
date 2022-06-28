package com.bikk.filmlibrary.screens.details

import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bikk.filmlibrary.R
import com.bikk.filmlibrary.databinding.FragmentDetailsBinding
import com.bikk.filmlibrary.di.modules.local.RoomModuleInt
import com.bikk.filmlibrary.models.MovieItemModel
import com.bikk.filmlibrary.screens.main.MainFragment
import com.bikk.filmlibrary.screens.main.MainViewModel
import com.bikk.filmlibrary.util.Const.FAVORITE_BTN_IS_ACTIVE
import com.bikk.filmlibrary.util.Const.FAVORITE_BTN_NOT_ACTIVE
import com.bikk.filmlibrary.util.SaveSharedImpl
import com.bikk.filmlibrary.util.SavedShared
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject
import org.koin.core.scope.Scope

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val viewBinding: FragmentDetailsBinding by viewBinding()
    lateinit var currentMovie: MovieItemModel
    private val scope: Scope = getKoin().createScope<DetailsFragment>()
    private val viewModel: DetailsViewModel = scope.get()
    val repo: RoomModuleInt by inject()
    private val savedShared: SavedShared = SaveSharedImpl()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentMovie = arguments?.getSerializable("movie") as MovieItemModel
        init()
        setFavorite()

    }

    private fun setFavorite() {
        fun updateBtnFavoriteIsNotActive() {
            viewBinding.imgDetailFavorite.setImageResource(
                FAVORITE_BTN_NOT_ACTIVE
            )
        }

        fun updateBtnFavoriteIsActive() {
            viewBinding.imgDetailFavorite.setImageResource(
                FAVORITE_BTN_IS_ACTIVE
            )
        }

        fun saveStateFavoriteValue(boolean: Boolean) {
            savedShared.setFavorite(requireContext(), currentMovie.id.toString(), boolean)
        }

        var isFavorite = false
        fun updateFavoriteButton(isFavorite: Boolean, valueBool: Boolean) {
            when {
                isFavorite != valueBool -> updateBtnFavoriteIsActive()
                else -> updateBtnFavoriteIsNotActive()
            }
        }

        val valueBool = savedShared.getFavorite(requireContext(), currentMovie.id.toString())
        updateFavoriteButton(isFavorite, valueBool)
        isFavorite = valueBool
        viewBinding.imgDetailFavorite.setOnClickListener {
            isFavorite = if (!isFavorite) {
                updateBtnFavoriteIsActive()
                saveStateFavoriteValue(true)
                viewModel.insert(currentMovie)
                true
            } else {
                updateBtnFavoriteIsNotActive()
                saveStateFavoriteValue(false)
                viewModel.delete(currentMovie)
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