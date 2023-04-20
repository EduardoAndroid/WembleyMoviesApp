package com.eduardandroid.wembleymoviesapp.data.usecase

import com.eduardandroid.wembleymoviesapp.data.model.MovieParamsBody
import com.eduardandroid.wembleymoviesapp.data.network.Resource
import com.eduardandroid.wembleymoviesapp.data.repository.MovieRepository
import javax.inject.Inject

class GetSearchListMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(page: Int, query: String): Resource<MovieParamsBody?>? {
        return repository.getSearchListMovies(page, query)
    }
}