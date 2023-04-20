package com.eduardandroid.wembleymoviesapp.data.usecase

import com.eduardandroid.wembleymoviesapp.data.model.MovieParamsBody
import com.eduardandroid.wembleymoviesapp.data.network.Resource
import com.eduardandroid.wembleymoviesapp.data.repository.MovieRepository
import javax.inject.Inject

class GetListMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(page: Int): Resource<MovieParamsBody?>? {
        return repository.getListMovies(page)
    }
}