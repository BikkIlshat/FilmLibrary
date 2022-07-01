package com.bikk.filmlibrary.screens.details

import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.bikk.filmlibrary.R
import com.bikk.filmlibrary.databinding.FragmentDetailsBinding
import com.bikk.filmlibrary.models.movies.MovieItemModel
import com.bikk.filmlibrary.util.Const
import com.bikk.filmlibrary.util.Const.FAVORITE_BTN_IS_ACTIVE
import com.bikk.filmlibrary.util.Const.FAVORITE_BTN_NOT_ACTIVE
import com.bikk.filmlibrary.util.SaveSharedImpl
import com.bikk.filmlibrary.util.SavedShared
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
        with(viewBinding) {
            imgDetail.load("${Const.BASE_IMAGE_URL}${currentMovie?.poster_path}")
            tvTitle.text = currentMovie?.title
            tvDate.text = currentMovie?.release_date
            tvDescription.text = currentMovie?.overview
            tvDescription.justificationMode = JUSTIFICATION_MODE_INTER_WORD
        }
    }


}