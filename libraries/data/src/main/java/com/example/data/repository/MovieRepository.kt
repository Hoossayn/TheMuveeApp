package com.example.data.repository

import com.example.data.model.MovieEntity

interface MovieRepository {
    suspend fun getMoviesFromRemote(language:String, page:Int): List<MovieEntity>

    suspend fun getMoviesFromLocal(): List<MovieEntity>

    suspend fun saveMovies(moviesEntity: List<MovieEntity>)

    suspend fun deleteMovies()
}