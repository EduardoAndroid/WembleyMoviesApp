package com.eduardandroid.wembleymoviesapp.usecase.screen.moviesList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eduardandroid.wembleymoviesapp.data.model.MovieBody
import com.eduardandroid.wembleymoviesapp.data.network.Resource
import com.eduardandroid.wembleymoviesapp.data.usecase.GetListMoviesUseCase
import com.eduardandroid.wembleymoviesapp.data.usecase.GetSearchListMoviesUseCase
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

    private val _paramMovies = MutableLiveData<ResponseMovies>()
    var paramMovies: LiveData<ResponseMovies> = _paramMovies

    private val _paramAddMovie = MutableLiveData<MoviesLoval>()
    var paramAddMovie: LiveData<MoviesLoval> = _paramAddMovie

    sealed class ResponseMovies {
        data class Success(val movieParamsBody: List<MovieBody>?, val refreshList: Boolean): ResponseMovies()
        data class SuccessSearch(val movieParamsBody: List<MovieBody>?): ResponseMovies()
        data class SuccessSearchPaging(val movieParamsBody: List<MovieBody>?): ResponseMovies()
    }

    sealed class MoviesLoval {
        data class AddMovie(val movieBody: MovieBody): MoviesLoval()
        data class RemoveMovie(val movieBody: MovieBody): MoviesLoval()
    }

    private var pageNumber = 1
    private var pageNumberSearch = 1

    fun getListMovies(refreshList: Boolean) {
        viewModelScope.launch {
            val favoriteMovies = getMovieLocalUseCase.invoke()

            when (val response = getListMoviesUseCase.invoke(pageNumber)) {
                is Resource.Error -> {
                    val truee = true
                }
                Resource.Loading -> {
                    val truee = true
                }
                is Resource.Success -> {
                    if (response.data != null) {
                        response.data.page?.let {
                            pageNumber = it + 1
                        }
                        val movies = response.data.results?.map { movieBody ->
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
                        _paramMovies.postValue(ResponseMovies.Success(movies, refreshList))
                    }
                }
                null -> {
                    val truee = true
                }
            }
        }
    }

    fun addMovieFavorite(movieBody: MovieBody) {
        viewModelScope.launch {
            addMovieUseCase.invoke(movieBody)
            _paramAddMovie.postValue(MoviesLoval.AddMovie(movieBody))
        }
    }

    fun removeMovieFavorite(movieBody: MovieBody) {
        viewModelScope.launch {
            removeMovieUseCase.invoke(movieBody)
            _paramAddMovie.postValue(MoviesLoval.RemoveMovie(movieBody))
        }
    }

    fun getSearchListMovies(query: String, paging: Boolean) {
        viewModelScope.launch {
            val favoriteMovies = getMovieLocalUseCase.invoke()

            when (val response = getSearchListMoviesUseCase.invoke(pageNumberSearch, query)) {
                is Resource.Error -> {
                    val truee = true
                }
                Resource.Loading -> {
                    val truee = true
                }
                is Resource.Success -> {
                    if (response.data != null) {
                        response.data.page?.let {
                            pageNumberSearch = it + 1
                        }
                        val movies = response.data.results?.map { movieBody ->
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
                        if (paging) {
                            _paramMovies.postValue(ResponseMovies.SuccessSearchPaging(movies))
                        } else {
                            _paramMovies.postValue(ResponseMovies.SuccessSearch(movies))
                        }
                    }
                }
                null -> {
                    val truee = true
                }
            }
        }
    }
}