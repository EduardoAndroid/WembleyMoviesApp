package com.eduardandroid.wembleymoviesapp.provider.room.roomListMovies.useCase

import com.eduardandroid.wembleymoviesapp.data.model.MovieBody
import com.eduardandroid.wembleymoviesapp.data.repository.MovieRepository
import com.eduardandroid.wembleymoviesapp.provider.room.roomListMovies.MovieRoomRepository
import javax.inject.Inject

class AddMovieUseCase @Inject constructor(private val repository: MovieRoomRepository) {
    suspend operator fun invoke(movie: MovieBody) {
        repository.add(movie)
    }
}