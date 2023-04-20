package com.eduardandroid.wembleymoviesapp.provider.repository

import com.eduardandroid.wembleymoviesapp.data.model.MovieBody
import com.eduardandroid.wembleymoviesapp.provider.room.roomListMovies.useCase.AddMovieUseCase
import com.eduardandroid.wembleymoviesapp.provider.room.roomListMovies.useCase.GetMovieUseCase
import javax.inject.Inject

class RepositoryMoviesLocal @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase,
    private val addMovieUseCase: AddMovieUseCase
) {

    suspend fun getMovies(): List<MovieBody>? {
        return getMovieUseCase.invoke()
    }

    suspend fun addMovie(movie: MovieBody) {
        addMovieUseCase.invoke(movie = movie)
    }
}