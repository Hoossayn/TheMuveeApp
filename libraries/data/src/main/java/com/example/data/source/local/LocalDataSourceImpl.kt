package com.example.data.source.local

import com.example.data.db.MovieDao
import com.example.data.model.MovieEntity
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val movieDao: MovieDao
):LocalMoviesDataSource {

    override suspend fun getMoviesEntities(): List<MovieEntity> {
        return movieDao.getMovieList()
    }

    override suspend fun saveEntities(posts: List<MovieEntity>): List<Long> {
        return movieDao.insert(posts)
    }

    override suspend fun deletePostEntities() {
        movieDao.deleteAll()
    }
}