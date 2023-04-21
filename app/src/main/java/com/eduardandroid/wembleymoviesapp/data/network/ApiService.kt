package com.eduardandroid.wembleymoviesapp.data.network

import com.eduardandroid.wembleymoviesapp.data.model.MovieParamsBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiService {

    @GET("movie/popular")
    suspend fun getListPopularMovies(@QueryMap body: Map<String, String>): Response<MovieParamsBody>

    @GET("search/movie")
    suspend fun getSearchMovie(@QueryMap body: Map<String, String>): Response<MovieParamsBody>
}