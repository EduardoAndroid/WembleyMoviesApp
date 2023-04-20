package com.eduardandroid.wembleymoviesapp.usecase.screen.moviegallery

import com.eduardandroid.wembleymoviesapp.data.model.MovieBody

interface OnMovieRemoveToFavoritesListener {
    fun onMovieAddedToFavorites(movie: MovieBody)
}