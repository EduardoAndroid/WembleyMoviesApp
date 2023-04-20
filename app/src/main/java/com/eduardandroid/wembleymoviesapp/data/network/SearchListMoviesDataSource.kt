package com.eduardandroid.wembleymoviesapp.data.network

import com.eduardandroid.wembleymoviesapp.data.model.MovieParamsBody
import com.eduardandroid.wembleymoviesapp.data.source.ApiModule
import javax.inject.Inject

class SearchListMoviesDataSource @Inject constructor(
    private val apiService: ApiService
    ) : IListMoviesUseCase {

    override suspend fun getListPopularMovies(page: Int, query: String): Resource<MovieParamsBody?>? {
        val body = HashMap<String, String>()
        body["language"] = "en-US"
        body["query"] = query
        body["page"] = page.toString()
        return try {
            val response = apiService.getSearchMovie(body)
            if (response.isSuccessful) {
                response.body()?.let {
                    Resource.success(it)
                } ?: Resource.error("No se pudieron cargar los datos", null)
            } else {
                Resource.error("No se pudieron cargar los datos", null)
            }
        } catch (e: Exception) {
            Resource.error("Algo salió mal. Intenta de nuevo más tarde", null)
        }
    }
}