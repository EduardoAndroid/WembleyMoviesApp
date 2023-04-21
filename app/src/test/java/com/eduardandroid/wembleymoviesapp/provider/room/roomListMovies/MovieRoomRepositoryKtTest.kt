package com.eduardandroid.wembleymoviesapp.provider.room.roomListMovies

import com.eduardandroid.wembleymoviesapp.data.model.MovieBody
import com.google.gson.Gson
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations.openMocks
import org.mockito.junit.MockitoJUnitRunner
import java.io.FileInputStream


@RunWith(MockitoJUnitRunner::class)
class MovieRoomRepositoryKtTest {

    private val gson = Gson()
    private lateinit var movieEntity: MovieEntity

    @Before
    fun setup() {
        openMocks(this)

        val movieEntityDetail = FileInputStream("src/main/assets/movieentity.json")
        movieEntity = gson.fromJson(movieEntityDetail.bufferedReader(), MovieEntity::class.java)
    }

    @Test
    fun test_parse_moviesentity_to_moviesbody() {
        val movieBody = MovieBody(
            idMovie = 123,
            posterPath = "/abc.jpg",
            adult = false,
            overview = "This is a movie about testing",
            releaseDate = "2023-01-01",
            originalTitle = "Test Movie",
            originalLanguage = "en",
            title = "Test Movie",
            backdropPath = "/xyz.jpg",
            popularity = 7.8,
            voteCount = 100,
            video = false,
            voteAverage = 7.5,
            isFavorite = true
        )

        //toData() para obtener resultados de MovieEntity
        val movieEntity = movieBody.toData()

        //Verificar valores de MovieEntity sean los mismos que se esperan
        assertEquals(movieBody.idMovie, movieEntity.id)
        assertEquals(movieBody.posterPath, movieEntity.posterPath)
        assertEquals(movieBody.adult, movieEntity.adult)
        assertEquals(movieBody.overview, movieEntity.overview)
        assertEquals(movieBody.releaseDate, movieEntity.releaseDate)
        assertEquals(movieBody.originalTitle, movieEntity.originalTitle)
        assertEquals(movieBody.originalLanguage, movieEntity.originalLanguage)
        assertEquals(movieBody.title, movieEntity.title)
        assertEquals(movieBody.backdropPath, movieEntity.backdropPath)
        assertEquals(movieBody.popularity, movieEntity.popularity)
        assertEquals(movieBody.voteCount, movieEntity.voteCount)
        assertEquals(movieBody.video, movieEntity.video)
        assertEquals(movieBody.voteAverage, movieEntity.voteAverage)
        assertEquals(movieBody.isFavorite, movieEntity.isFavorite)
    }
}

/*fun MovieBody.toData(): MovieEntity {
    return MovieEntity(this.idMovie, this.posterPath, this.adult, this.overview, this.releaseDate,
        this.originalTitle, this.originalLanguage, this.title, this.backdropPath,
        this.popularity, this.voteCount, this.video, this.voteAverage, this.isFavorite)
}*/