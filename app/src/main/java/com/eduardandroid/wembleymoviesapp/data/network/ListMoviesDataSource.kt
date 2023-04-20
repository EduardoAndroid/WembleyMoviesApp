package com.eduardandroid.wembleymoviesapp.data.network

import com.eduardandroid.wembleymoviesapp.data.model.MovieParamsBody
import com.eduardandroid.wembleymoviesapp.domain.interfaceUseCase.IListSearchMoviesUseCase
import javax.inject.Inject

class ListMoviesDataSource @Inject constructor(
    private val apiService: ApiService
    ) : IListSearchMoviesUseCase {

    override suspend fun getListPopularMovies(page: Int): Resource<MovieParamsBody?>? {
        val body = HashMap<String, String>()
        body["language"] = "en-US"
        body["page"] = page.toString()
        return try {
            val response = apiService.getListPopularMovies(body)
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