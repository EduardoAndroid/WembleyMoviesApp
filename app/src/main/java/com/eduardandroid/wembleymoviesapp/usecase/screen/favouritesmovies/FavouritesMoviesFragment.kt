package com.eduardandroid.wembleymoviesapp.usecase.screen.favouritesmovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.GridLayoutManager
import com.eduardandroid.wembleymoviesapp.R
import com.eduardandroid.wembleymoviesapp.data.model.MovieBody
import com.eduardandroid.wembleymoviesapp.databinding.FragmentFavouritesMoviesBinding
import com.eduardandroid.wembleymoviesapp.usecase.screen.adapter.adapterlistmovies.AdapterListMoviesFavorites
import com.eduardandroid.wembleymoviesapp.usecase.screen.moviegallery.OnMovieRemoveToFavoritesListener
import com.eduardandroid.wembleymoviesapp.usecase.screen.moviesList.ListMoviesFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavouritesMoviesFragment: Fragment() {

    private lateinit var binding: FragmentFavouritesMoviesBinding
    val viewModel: FavouritesMoviesViewModel by viewModels()

    @Inject
    lateinit var mAdapter: AdapterListMoviesFavorites

    companion object {
        fun newInstance() = FavouritesMoviesFragment().apply {
            arguments = Bundle().apply {
                //putString(KEY_LINK_USER, link)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favourites_movies, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
    }

    override fun onResume() {
        super.onResume()
        initAdapter()
    }

    private fun initObservers() {
        viewModel.viewParam.observe(viewLifecycleOwner) {
            when (it) {
                is FavouritesMoviesViewModel.ListMovies.Success -> {
                    if (it.listMovies != null && it.listMovies.isNotEmpty()) {
                        binding.clEmptyState.visibility = View.GONE
                        mAdapter.setData(it.listMovies as MutableList<MovieBody>?)
                    } else {
                        binding.clEmptyState.visibility = View.VISIBLE
                        Toast.makeText(context, "Parece que no tiene datos", Toast.LENGTH_SHORT).show()
                    }
                }
                is FavouritesMoviesViewModel.ListMovies.RemoveMovie -> {
                    mAdapter.removeMovie(movieBody = it.movieBody, position = it.position)
                    (activity as? OnMovieRemoveToFavoritesListener)?.onMovieAddedToFavorites(it.movieBody)
                }
            }
        }
    }

    private fun initAdapter() {
        binding.rvListMoviesFavorites.apply {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = mAdapter
            viewModel.getListFavoritesMovies()

            mAdapter.onClickFavoriteMovie = { movieBody, position ->
                viewModel.removeMovieFavorite(movieBody, position)
            }
        }
    }
}