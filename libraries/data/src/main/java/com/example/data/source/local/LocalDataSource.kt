package com.example.data.source.local

import com.example.data.model.movies.MovieEntity
import com.example.data.model.shows.ShowsEntity


interface LocalMoviesDataSource  {
    suspend fun getMoviesEntities(): List<MovieEntity>
    suspend fun saveMoviesEntities(posts: List<MovieEntity>): List<Long>
    suspend fun deletePostEntities()

    suspend fun getShowsEntities(): List<ShowsEntity>
    suspend fun saveShowsEntities(posts: List<ShowsEntity>): List<Long>
}