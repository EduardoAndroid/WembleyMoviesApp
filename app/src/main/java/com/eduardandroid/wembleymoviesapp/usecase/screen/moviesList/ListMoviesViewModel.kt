package com.eduardandroid.wembleymoviesapp.usecase.screen.moviesList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eduardandroid.wembleymoviesapp.data.model.MovieBody
import com.eduardandroid.wembleymoviesapp.data.network.Resource
import com.eduardandroid.wembleymoviesapp.domain.useCase.GetListMoviesUseCase
import com.eduardandroid.wembleymoviesapp.domain.useCase.GetSearchListMoviesUseCase
import com.eduardandroid.wembleymoviesapp.domain.useCase.AddMovieRepositoryLocalUseCase
import com.eduardandroid.wembleymoviesapp.domain.useCase.GetMovieRepositoryLocalUseCase
import com.eduardandroid.wembleymoviesapp.provider.room.roomListMovies.useCase.RemoveMovieRepositoryLocalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListMoviesViewModel @Inject constructor(
    private val getListMoviesUseCase: GetListMoviesUseCase,
    private val addMovieUseCase: AddMovieRepositoryLocalUseCase,
    private val removeMovieUseCase: RemoveMovieRepositoryLocalUseCase,
    private val getMovieLocalUseCase: GetMovieRepositoryLocalUseCase,
    private val getSearchListMoviesUseCase: GetSearchListMoviesUseCase
): ViewModel() {

    private val _observerMovies = MutableLiveData<ResponseMovies>()
    var observerMovies: LiveData<ResponseMovies> = _observerMovies

    private val _observerAddRemoveMovie = MutableLiveData<MoviesLocal>()
    var observerAddRemoveMovie: LiveData<MoviesLocal> = _observerAddRemoveMovie

    sealed class ResponseMovies {
        data class Success(val movieParamsBody: List<MovieBody>?, val refreshList: Boolean): ResponseMovies()
        data class SuccessSearch(val movieParamsBody: List<MovieBody>?): ResponseMovies()
        data class SuccessSearchPaging(val movieParamsBody: List<MovieBody>?): ResponseMovies()
        object ErrorLoad: ResponseMovies()
    }

    sealed class MoviesLocal {
        data class AddMovie(val movieBody: MovieBody): MoviesLocal()
        data class RemoveMovie(val movieBody: MovieBody): MoviesLocal()
        object ErrorLoad: MoviesLocal()
    }

    private var pageNumber = 1
    private var pageNumberSearch = 1

    fun getListMovies(refreshList: Boolean) {
        viewModelScope.launch {
            when (val response = getListMoviesUseCase.invoke(pageNumber)) {
                is Resource.Error -> {
                    _observerMovies.postValue(ResponseMovies.ErrorLoad)
                }
                Resource.Loading -> { }
                is Resource.Success -> {
                    if (response.data != null) {
                        response.data.page?.let {
                            pageNumber = it + 1
                        }
                        val movies = returnMoviesMap(response.data.results)
                        _observerMovies.postValue(ResponseMovies.Success(movies, refreshList))
                    }
                }
                null -> {
                    _observerMovies.postValue(ResponseMovies.ErrorLoad)
                }
            }
        }
    }

    fun addMovieFavorite(movieBody: MovieBody) {
        viewModelScope.launch {
            addMovieUseCase.invoke(movieBody)
            _observerAddRemoveMovie.postValue(MoviesLocal.AddMovie(movieBody))
        }
    }

    fun removeMovieFavorite(movieBody: MovieBody) {
        viewModelScope.launch {
            removeMovieUseCase.invoke(movieBody)
            _observerAddRemoveMovie.postValue(MoviesLocal.RemoveMovie(movieBody))
        }
    }

    fun getSearchListMovies(query: String, paging: Boolean) {
        viewModelScope.launch {
            when (val response = getSearchListMoviesUseCase.invoke(pageNumberSearch, query)) {
                is Resource.Error -> {
                    _observerMovies.postValue(ResponseMovies.ErrorLoad)
                }
                Resource.Loading -> {
                    val truee = true
                }
                is Resource.Success -> {
                    if (response.data != null) {
                        response.data.page?.let {
                            pageNumberSearch = it + 1
                        }
                        val movies = returnMoviesMap(response.data.results)
                        if (paging) {
                            _observerMovies.postValue(ResponseMovies.SuccessSearchPaging(movies))
                        } else {
                            _observerMovies.postValue(ResponseMovies.SuccessSearch(movies))
                        }
                    }
                }
                null -> {
                    _observerMovies.postValue(ResponseMovies.ErrorLoad)
                }
            }
        }
    }

    private suspend fun returnMoviesMap(results: MutableList<MovieBody>?): List<MovieBody>? {
        val favoriteMovies = getMovieLocalUseCase.invoke()
        return results?.map { movieBody ->
            MovieBody(
                posterPath = movieBody.posterPath,
                adult = movieBody.adult,
                overview = movieBody.overview,
                releaseDate = movieBody.releaseDate,
                idMovie = movieBody.idMovie,
                originalTitle = movieBody.originalTitle,
                originalLanguage = movieBody.originalLanguage,
                title = movieBody.title,
                backdropPath = movieBody.backdropPath,
                popularity = movieBody.popularity,
                voteCount = movieBody.voteCount,
                video = movieBody.video,
                voteAverage = movieBody.voteAverage,
                isFavorite = favoriteMovies?.any { it.idMovie == movieBody.idMovie }
            )
        }
    }
}