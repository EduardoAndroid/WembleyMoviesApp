package com.eduardandroid.wembleymoviesapp.data.repository

import com.eduardandroid.wembleymoviesapp.data.model.MovieParamsBody
import com.eduardandroid.wembleymoviesapp.data.network.ListMoviesDataSource
import com.eduardandroid.wembleymoviesapp.data.network.Resource
import com.eduardandroid.wembleymoviesapp.data.network.SearchListMoviesDataSource
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val listMoviesDataSource: ListMoviesDataSource,
    private val searchListMoviesDataSource: SearchListMoviesDataSource
) {
    suspend fun getListMovies(page: Int): Resource<MovieParamsBody?>? {
        return listMoviesDataSource.getListPopularMovies(page)
    }

    suspend fun getSearchListMovies(page: Int, query: String): Resource<MovieParamsBody?>? {
        return  searchListMoviesDataSource.getListPopularMovies(page, query)
    }
}