package com.example.data.di

import android.app.Application
import androidx.room.Room
import com.example.data.db.DATABASE_NAME
import com.example.data.db.dao.MovieDao
import com.example.data.db.MovieDatabase
import com.example.data.db.dao.ShowsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun providesDatabase(application:Application): MovieDatabase{
        return Room.databaseBuilder(
            application,
            MovieDatabase::class.java,
            DATABASE_NAME
        ).build()

    }

    @Singleton
    @Provides
    fun provideMoviesDao(appDatabase: MovieDatabase): MovieDao = appDatabase.movieDao()


    @Singleton
    @Provides
    fun provideShowsDao(appDatabase: MovieDatabase): ShowsDao = appDatabase.showsDao()
}