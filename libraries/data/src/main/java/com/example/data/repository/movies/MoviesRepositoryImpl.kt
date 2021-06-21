package com.example.data.repository.movies

import android.util.Log
import com.example.data.mapper.MoviesDTOtoEntityMapper
import com.example.data.model.movies.MovieEntity
import com.example.data.source.local.LocalMoviesDataSource
import com.example.data.source.remote.RemoteDataSource
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val localDataSource: LocalMoviesDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val mapperMovies: MoviesDTOtoEntityMapper
): MovieRepository {

    override suspend fun getMoviesFromRemote(language:String, page:Int): List<MovieEntity> {
        val movieDTOList = remoteDataSource.getMovies(language,page)
        return mapperMovies.map(movieDTOList)
    }
    override suspend fun getMoviesFromLocal(): List<MovieEntity> {
        return localDataSource.getMoviesEntities()
    }

    override suspend fun saveMovies(moviesEntity: List<MovieEntity>) {
        localDataSource.saveMoviesEntities(moviesEntity)
    }

    override suspend fun deleteMovies() {
        localDataSource.deletePostEntities()
    }

}