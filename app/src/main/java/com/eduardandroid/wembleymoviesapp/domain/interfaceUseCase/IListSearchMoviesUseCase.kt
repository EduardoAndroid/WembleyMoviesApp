package com.eduardandroid.wembleymoviesapp.domain.interfaceUseCase

import com.eduardandroid.wembleymoviesapp.data.model.MovieParamsBody
import com.eduardandroid.wembleymoviesapp.data.network.Resource

interface IListSearchMoviesUseCase {
    suspend fun getListPopularMovies(page: Int): Resource<MovieParamsBody?>?
}