package com.bikk.filmlibrary.screens.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bikk.filmlibrary.R
import com.bikk.filmlibrary.databinding.FragmentMainBinding
import com.bikk.filmlibrary.models.MovieItemModel
import com.bikk.filmlibrary.screens.main.adapter.MoviesListAdapter
import com.bikk.filmlibrary.screens.main.adapter.OnClickListener
import org.koin.android.ext.android.getKoin
import org.koin.core.scope.Scope

class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewBinding: FragmentMainBinding by viewBinding()
    private var adapter: MoviesListAdapter? = null
    private val scope: Scope = getKoin().createScope<MainFragment>()
    private val viewModel: MainViewModel = scope.get()
    private var isFirst = true


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        setHasOptionsMenu(true)
    }


    private fun init() = with(viewBinding) {
        if (isFirst) {
            adapter = MoviesListAdapter(object : OnClickListener {
                override fun onClick(listMovies: MovieItemModel) {
                    val bundle = bundleOf()
                    bundle.putSerializable("movie", listMovies)
                    findNavController().navigate(
                        R.id.action_mainFragment_to_detailsFragment,
                        bundle
                    )
                }
            })

            viewModel.getMoviesRetrofit()
            isFirst = false
            viewModel.mMovies.observe(viewLifecycleOwner) {
                adapter?.submitList(it?.body()!!.results)
            }
        }
        rvMain.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_favorite -> {
                findNavController().navigate(R.id.action_mainFragment_to_favoriteFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}