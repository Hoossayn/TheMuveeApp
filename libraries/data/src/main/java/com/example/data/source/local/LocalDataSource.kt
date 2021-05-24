package com.example.data.source.local

import com.example.data.model.MovieEntity



interface LocalMoviesDataSource  {
    suspend fun getMoviesEntities(): List<MovieEntity>
    suspend fun saveEntities(posts: List<MovieEntity>): List<Long>
    suspend fun deletePostEntities()
}