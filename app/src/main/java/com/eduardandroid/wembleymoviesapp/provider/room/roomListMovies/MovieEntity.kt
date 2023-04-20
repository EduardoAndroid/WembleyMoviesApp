package com.eduardandroid.wembleymoviesapp.provider.room.roomListMovies

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "MovieEntity")
data class MovieEntity(
    @PrimaryKey
    val id: Int? = null,
    var posterPath: String?= null,
    var adult: Boolean?= null,
    var overview: String?= null,
    var releaseDate: String?= null,
    var originalTitle: String?= null,
    var originalLanguage: String?= null,
    var title: String?= null,
    var backdropPath: String?= null,
    var popularity: Double?= null,
    var voteCount: Int?= null,
    var video: Boolean?= null,
    var voteAverage: Double?= null,
    var isFavorite: Boolean? = false
)
