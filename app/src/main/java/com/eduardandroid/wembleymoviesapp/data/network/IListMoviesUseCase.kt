package com.eduardandroid.wembleymoviesapp.data.network

import com.eduardandroid.wembleymoviesapp.data.model.MovieParamsBody

interface IListMoviesUseCase {
    suspend fun getListPopularMovies(page: Int): Resource<MovieParamsBody?>?
}