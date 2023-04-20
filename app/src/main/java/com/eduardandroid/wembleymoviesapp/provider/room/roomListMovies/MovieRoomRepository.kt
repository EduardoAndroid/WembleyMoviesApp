package com.eduardandroid.wembleymoviesapp.provider.room.roomListMovies

import com.eduardandroid.wembleymoviesapp.data.model.MovieBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRoomRepository @Inject constructor(private val taskDao: MovieDao) {

    //val tasks: Flow<List<ProductItemBody>> =
    //taskDao.getTasks().map { items -> items.map { ProductItemBody(it.id, it.product) } }

    suspend fun getAllSessions(): MutableList<MovieBody> {
        return parseProductEntityToProductItemBody(taskDao.getTasks())
    }

    suspend fun add(taskModel: MovieBody) {
        taskDao.addTask(taskModel.toData())
    }

    suspend fun update(taskModel: MovieBody) {
        taskDao.updateTask(taskModel.toData())
    }

    suspend fun delete(taskModel: MovieBody) {
        taskDao.deleteTask(taskModel.toData())
    }
}

fun MovieBody.toData(): MovieEntity {
    return MovieEntity(this.idMovie, this.posterPath, this.adult, this.overview, this.releaseDate,
        this.originalTitle, this.originalLanguage, this.title, this.backdropPath,
        this.popularity, this.voteCount, this.video, this.voteAverage, this.isFavorite)
}

fun parseProductEntityToProductItemBody(tasks: List<MovieEntity>?): MutableList<MovieBody> {
    val listProductsReturn: MutableList<MovieBody> = mutableListOf()
    tasks?.let {
        for (item in tasks) {
            listProductsReturn.add(MovieBody(item.posterPath, item.adult, item.overview,
                item.releaseDate, item.id, item.originalTitle, item.originalLanguage,
                item.title, item.backdropPath, item.popularity, item.voteCount, item.video,
                item.voteAverage, item.isFavorite))
        }
    }
    return listProductsReturn
}