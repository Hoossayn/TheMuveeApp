package com.example.core.di

import com.example.data.di.DatabaseModule
import com.example.data.di.NetworkModule
import com.example.data.mapper.MoviesDTOtoEntityMapper
import com.example.data.mapper.ShowsDTOtoEntityMapper
import com.example.data.repository.movies.MovieRepository
import com.example.data.repository.movies.MoviesRepositoryImpl
import com.example.data.repository.shows.ShowsRepository
import com.example.data.repository.shows.ShowsRepositoryImpl
import com.example.data.source.local.LocalDataSourceImpl
import com.example.data.source.local.LocalMoviesDataSource
import com.example.data.source.remote.RemoteDataSource
import com.example.data.source.remote.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module(
    includes = [
        DataProviderModule::class,
        NetworkModule::class,
        DatabaseModule::class
    ]
)
interface DataModule {

    @Singleton
    @Binds
    fun bindRemoteDataSource(remoteDataSource: RemoteDataSourceImpl): RemoteDataSource

    @Singleton
    @Binds
    fun bindLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalMoviesDataSource

    @Singleton
    @Binds
    fun bindRepository(repository: MoviesRepositoryImpl): MovieRepository

    @Singleton
    @Binds
    fun bindShowsRepository(repository: ShowsRepositoryImpl): ShowsRepository
}

/**
 * This module is for injections with @Provides annotation
 */
@Module
@InstallIn(ApplicationComponent::class)
object DataProviderModule {
    @Provides
    fun provideDTOtoEntityMapper() = MoviesDTOtoEntityMapper()

    @Provides
    fun provideShowDTOToEntity ()= ShowsDTOtoEntityMapper()
}
