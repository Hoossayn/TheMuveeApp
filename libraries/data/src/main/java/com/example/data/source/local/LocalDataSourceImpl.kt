package com.example.data.source.local

import com.example.data.db.dao.MovieDao
import com.example.data.db.dao.ShowsDao
import com.example.data.model.movies.MovieEntity
import com.example.data.model.shows.ShowsEntity
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val movieDao: MovieDao,
    private val showsDao:ShowsDao
):LocalMoviesDataSource {

    override suspend fun getMoviesEntities(): List<MovieEntity> {
        return movieDao.getMovieList()
    }

    override suspend fun saveMoviesEntities(movies: List<MovieEntity>): List<Long> {
        return movieDao.insert(movies)
    }

    override suspend fun deletePostEntities() {
        movieDao.deleteAll()
    }


    override suspend fun getShowsEntities(): List<ShowsEntity> {
        return showsDao.getShowList()
    }

    override suspend fun saveShowsEntities(shows: List<ShowsEntity>): List<Long> {
        return showsDao.insert(shows)
    }
}