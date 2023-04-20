package com.eduardandroid.wembleymoviesapp.domain.interfaceUseCase

import com.eduardandroid.wembleymoviesapp.data.model.MovieParamsBody
import com.eduardandroid.wembleymoviesapp.data.network.Resource

interface IListMoviesUseCase {
    suspend fun getListPopularMovies(page: Int, query: String): Resource<MovieParamsBody?>?
}