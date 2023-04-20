package com.eduardandroid.wembleymoviesapp.data.repository

import com.eduardandroid.wembleymoviesapp.data.model.MovieParamsBody
import com.eduardandroid.wembleymoviesapp.data.network.ListMoviesDataSource
import com.eduardandroid.wembleymoviesapp.data.network.Resource
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val listMoviesDataSource: ListMoviesDataSource
) {
    suspend fun getListMovies(page: Int): Resource<MovieParamsBody?>? {
        return listMoviesDataSource.getListPopularMovies(page)
    }
}