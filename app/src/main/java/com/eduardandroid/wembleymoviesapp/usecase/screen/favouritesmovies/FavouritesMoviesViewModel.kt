package com.eduardandroid.wembleymoviesapp.usecase.screen.favouritesmovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eduardandroid.wembleymoviesapp.data.model.MovieBody
import com.eduardandroid.wembleymoviesapp.domain.useCase.AddMovieRepositoryLocalUseCase
import com.eduardandroid.wembleymoviesapp.domain.useCase.GetMovieRepositoryLocalUseCase
import com.eduardandroid.wembleymoviesapp.provider.room.roomListMovies.useCase.RemoveMovieRepositoryLocalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesMoviesViewModel @Inject constructor(
    private val getMoviesUseCase: GetMovieRepositoryLocalUseCase,
    private val addMovieUseCase: AddMovieRepositoryLocalUseCase,
    private val removeMovieUseCase: RemoveMovieRepositoryLocalUseCase
): ViewModel() {

    private val _viewParam = MutableLiveData<ListMovies>()
    var viewParam: LiveData<ListMovies> = _viewParam

    sealed class ListMovies {
        data class Success(val listMovies: List<MovieBody>?): ListMovies()
        data class RemoveMovie(val movieBody: MovieBody, val position: Int): ListMovies()
    }

    fun getListFavoritesMovies() {
        viewModelScope.launch {
            val listItems = getMoviesUseCase.invoke()
            _viewParam.postValue(ListMovies.Success(listItems))
        }
    }

    fun removeMovieFavorite(movieBody: MovieBody, position: Int) {
        viewModelScope.launch {
            removeMovieUseCase.invoke(movieBody)
            _viewParam.postValue(ListMovies.RemoveMovie(movieBody, position))
        }
    }
}