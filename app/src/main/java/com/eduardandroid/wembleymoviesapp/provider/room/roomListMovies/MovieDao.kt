package com.eduardandroid.wembleymoviesapp.provider.room.roomListMovies

import androidx.room.*

@Dao
interface MovieDao {
    @Query("SELECT * from MovieEntity")
    suspend fun getTasks(): List<MovieEntity>

    @Insert
    suspend fun addTask(item: MovieEntity)

    @Update
    suspend fun updateTask(item: MovieEntity)

    @Delete
    suspend fun deleteTask(item: MovieEntity)
}