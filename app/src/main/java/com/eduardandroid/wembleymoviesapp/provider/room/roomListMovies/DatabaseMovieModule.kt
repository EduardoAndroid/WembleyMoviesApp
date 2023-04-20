package com.eduardandroid.wembleymoviesapp.provider.room.roomListMovies

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseMovieModule {

    @Provides
    fun provideTaskDao(todoDataBase: MovieDataBase): MovieDao {
        return todoDataBase.movieDao()
    }

    @Provides
    @Singleton
    fun provideTodoDataBase(@ApplicationContext appContext: Context): MovieDataBase {
        return Room.databaseBuilder(appContext, MovieDataBase::class.java, "MovieDatabase").build()
    }
}