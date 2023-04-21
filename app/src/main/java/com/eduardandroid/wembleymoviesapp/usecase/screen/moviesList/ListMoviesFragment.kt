package com.eduardandroid.wembleymoviesapp.usecase.screen.moviesList

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eduardandroid.wembleymoviesapp.R
import com.eduardandroid.wembleymoviesapp.data.model.MovieBody
import com.eduardandroid.wembleymoviesapp.databinding.FragmentListMoviesBinding
import com.eduardandroid.wembleymoviesapp.usecase.screen.adapter.adapterlistmovies.AdapterListMovies
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ListMoviesFragment: Fragment() {

    private lateinit var binding: FragmentListMoviesBinding
    val viewModel: ListMoviesViewModel by viewModels()

    @Inject
    lateinit var mAdapter: AdapterListMovies

    private var searchQuery: String? = null

    companion object {
        fun newInstance() = ListMoviesFragment().apply {
            arguments = Bundle().apply {

            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_movies, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        initListeners()
        initAdapter()
    }

    private fun initListeners() {
        binding.etSearchText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                setTextWatcherSearchMovie(s)
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun setTextWatcherSearchMovie(s: CharSequence?) {
        if (s != null && s.isNotEmpty()) {
            searchQuery = s.toString()
            viewModel.getSearchListMovies(s.toString(), false)
        } else {
            searchQuery = null
            viewModel.getListMovies(true)
        }
    }

    private fun initObservers() {
        viewModel.observerMovies.observe(viewLifecycleOwner) {
            when (it) {
                is ListMoviesViewModel.ResponseMovies.Success -> {
                    mAdapter.setData(it.movieParamsBody as MutableList<MovieBody>, it.refreshList)
                }
                is ListMoviesViewModel.ResponseMovies.SuccessSearch -> {
                    mAdapter.setDataSearch(it.movieParamsBody as MutableList<MovieBody>)
                    visibleEmptyState(it.movieParamsBody.isEmpty())
                }
                is ListMoviesViewModel.ResponseMovies.SuccessSearchPaging -> {
                    mAdapter.setData(it.movieParamsBody as MutableList<MovieBody>, false)
                }
                ListMoviesViewModel.ResponseMovies.ErrorLoad -> {
                    visibleEmptyState(true)
                }
            }
        }
        viewModel.observerAddRemoveMovie.observe(viewLifecycleOwner) {
            when (it) {
                is ListMoviesViewModel.MoviesLocal.AddMovie -> {
                    if (it.movieBody.isFavorite == true) {
                        Toast.makeText(context, resources.getText(R.string.add_favorite), Toast.LENGTH_SHORT).show()
                    }
                }
                is ListMoviesViewModel.MoviesLocal.RemoveMovie -> {
                    if (it.movieBody.isFavorite == false) {
                        Toast.makeText(context, resources.getText(R.string.remove_favorite), Toast.LENGTH_SHORT).show()
                    }
                }
                ListMoviesViewModel.MoviesLocal.ErrorLoad -> {
                    visibleEmptyState(true)
                }
            }
        }
    }

    private fun visibleEmptyState(isVisible: Boolean) {
        if (isVisible) {
            binding.clEmptyState.visibility = View.VISIBLE
        } else {
            binding.clEmptyState.visibility = View.GONE
        }
    }

    private fun initAdapter() {
        binding.rvListMovies.apply {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = mAdapter
            viewModel.getListMovies(false)

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (!recyclerView.canScrollVertically(1)) {
                        if (searchQuery != null) {
                            viewModel.getSearchListMovies(searchQuery!!, true)
                        } else {
                            viewModel.getListMovies(false)
                        }
                    }
                }
            })
            
            mAdapter.onClickFavoriteMovie = { movieBody ->
                when (movieBody.isFavorite) {
                    true -> { viewModel.addMovieFavorite(movieBody) }
                    false -> { viewModel.removeMovieFavorite(movieBody)}
                    null -> {}
                }
            }
        }
    }

    fun refreshMovie(movieId: Int?, movie: MovieBody) {
        val position = movieId?.let { mAdapter.getPositionForMovie(it) }
        position?.let { if (it != -1) mAdapter.removeSelectionFavorite(it, movie) }
    }
}