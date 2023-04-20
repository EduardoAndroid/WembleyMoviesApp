package com.eduardandroid.wembleymoviesapp.provider.room.roomListMovies

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieEntity::class], version = 1)
abstract class MovieDataBase : RoomDatabase(){
    //DAO
    abstract fun movieDao(): MovieDao
}