package com.eduardandroid.wembleymoviesapp.usecase.screen.moviesList

import com.eduardandroid.wembleymoviesapp.data.model.MovieBody
import com.google.gson.Gson
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotSame
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations.openMocks
import org.mockito.junit.MockitoJUnitRunner
import java.io.FileInputStream

@RunWith(MockitoJUnitRunner::class)
class ListMoviesViewModelTest {

    private val gson = Gson()
    private lateinit var movieBody: MovieBody

    @Before
    fun setup() {
        openMocks(this)

        val detailMovie = FileInputStream("src/main/assets/detailmovie.json")

        movieBody = gson.fromJson(detailMovie.bufferedReader(), MovieBody::class.java)
    }

    @Test
    fun test_parse_movies_body_success() {
        val movieDetail = movieBody
        val expetedMovieDetail = MovieBody(
            posterPath = "/qNBAXBIQlnOThrVvA6mA2B5ggV6.jpg",
            adult = false,
            overview = "While working underground to fix a water main, Brooklyn plumbers—and brothers—Mario and Luigi are transported down a mysterious pipe and wander into a magical new world. But when the brothers are separated, Mario embarks on an epic quest to find Luigi.",
            releaseDate = "2023-04-05",
            idMovie = 502356,
            originalTitle = "The Super Mario Bros. Movie",
            originalLanguage = "en",
            title = "The Super Mario Bros. Movie",
            backdropPath = "/lWqjXgut48IK5f5IRbDBAoO2Epp.jpg",
            popularity = 11640.691,
            voteCount = 1130,
            video = false,
            voteAverage = 7.5,
            isFavorite = null
        )
        assertEquals(expetedMovieDetail, movieDetail)
    }

    @Test
    fun test_parse_movies_body_null_field_error() {
        val movieDetail = movieBody
        val expetedMovieDetail = MovieBody(
            posterPath = "/qNBAXBIQlnOThrVvA6mA2B5ggV6.jpg",
            adult = false,
            overview = "While working underground to fix a water main, Brooklyn plumbers—and brothers—Mario and Luigi are transported down a mysterious pipe and wander into a magical new world. But when the brothers are separated, Mario embarks on an epic quest to find Luigi.",
            releaseDate = "2023-04-05",
            idMovie = 502356,
            originalTitle = "The Super Mario Bros. Movie",
            originalLanguage = "en",
            title = "The Super Mario Bros. Movie",
            backdropPath = "/lWqjXgut48IK5f5IRbDBAoO2Epp.jpg",
            popularity = 11640.691,
            video = false,
            voteAverage = 7.5,
            isFavorite = null
        )
        assertNotSame(expetedMovieDetail, movieDetail)
    }

    @Test
    fun test_parse_movies_body_no_id_error() {
        val movieDetail = movieBody
        val expetedMovieDetail = MovieBody(
            posterPath = "/qNBAXBIQlnOThrVvA6mA2B5ggV6.jpg",
            adult = false,
            overview = "While working underground to fix a water main, Brooklyn plumbers—and brothers—Mario and Luigi are transported down a mysterious pipe and wander into a magical new world. But when the brothers are separated, Mario embarks on an epic quest to find Luigi.",
            releaseDate = "2023-04-05",
            idMovie = null,
            originalTitle = "The Super Mario Bros. Movie",
            originalLanguage = "en",
            title = "The Super Mario Bros. Movie",
            backdropPath = "/lWqjXgut48IK5f5IRbDBAoO2Epp.jpg",
            popularity = 11640.691,
            video = false,
            voteAverage = 7.5,
            isFavorite = null
        )
        assertNotSame(expetedMovieDetail, movieDetail)
    }
}