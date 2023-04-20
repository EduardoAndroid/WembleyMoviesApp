package com.eduardandroid.wembleymoviesapp.domain.useCase

import com.eduardandroid.wembleymoviesapp.data.model.MovieBody
import com.eduardandroid.wembleymoviesapp.provider.room.roomListMovies.MovieRoomRepository
import javax.inject.Inject

class AddMovieRepositoryLocalUseCase @Inject constructor(private val repository: MovieRoomRepository) {
    suspend operator fun invoke(movie: MovieBody) {
        repository.add(movie)
    }
}