package com.eduardandroid.wembleymoviesapp.data.model

import com.google.gson.annotations.SerializedName

data class MovieParamsBody(
    @SerializedName("page") var page: Int?= null,
    @SerializedName("results") var results: MutableList<MovieBody>? = null,
    @SerializedName("total_results") var totalResults: Int?= null,
    @SerializedName("total_pages") var totalPages: Int?= null
)

data class MovieBody (
    @SerializedName("poster_path") var posterPath: String?= null,
    @SerializedName("adult") var adult: Boolean?= null,
    @SerializedName("overview") var overview: String?= null,
    @SerializedName("release_date") var releaseDate: String?= null,
    @SerializedName("id") var idMovie: Int?= null,
    @SerializedName("original_title") var originalTitle: String?= null,
    @SerializedName("original_language") var originalLanguage: String?= null,
    @SerializedName("title") var title: String?= null,
    @SerializedName("backdrop_path") var backdropPath: String?= null,
    @SerializedName("popularity") var popularity: Double?= null,
    @SerializedName("vote_count") var voteCount: Int?= null,
    @SerializedName("video") var video: Boolean?= null,
    @SerializedName("vote_average") var voteAverage: Double?= null,
    @SerializedName("is_favorite") var isFavorite: Boolean?= null
)
