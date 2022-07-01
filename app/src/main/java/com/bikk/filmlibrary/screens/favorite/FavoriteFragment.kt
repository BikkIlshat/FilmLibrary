package com.bikk.filmlibrary.screens.favorite

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bikk.filmlibrary.R
import com.bikk.filmlibrary.databinding.FragmentFavoriteBinding
import com.bikk.filmlibrary.models.movies.MovieItemModel
import com.bikk.filmlibrary.screens.main.adapter.OnClickListener
import org.koin.android.ext.android.getKoin
import org.koin.core.scope.Scope

class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    private val viewBinding: FragmentFavoriteBinding by viewBinding()
    private val scope: Scope = getKoin().createScope<FavoriteFragment>()
    private val viewModel: FavoriteViewModel = scope.get()
    lateinit var recyclerView: RecyclerView
    private var adapter: FavoriteListAdapter? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = viewBinding.rvFavorite
        viewModel.getAllMovies().observe(viewLifecycleOwner){
            adapter?.submitList(it.asReversed())
        }
        adapter = FavoriteListAdapter(object : OnClickListener {
            override fun onClick(listMovies: MovieItemModel) {
                val bundle = bundleOf()
                bundle.putSerializable("movie", listMovies)
                findNavController().navigate(
                    R.id.action_favoriteFragment_to_detailsFragment, bundle
                )
            }
        })
        recyclerView.adapter = adapter
    }

}