package com.eduardandroid.wembleymoviesapp.domain.useCase

import com.eduardandroid.wembleymoviesapp.data.model.MovieBody
import com.eduardandroid.wembleymoviesapp.provider.repository.RepositoryMoviesLocal
import javax.inject.Inject

class GetMovieRepositoryLocalUseCase @Inject constructor(private val repositoryMoviesLocal: RepositoryMoviesLocal) {
    suspend operator fun invoke(): List<MovieBody>? {
        return repositoryMoviesLocal.getMovies()
    }
}