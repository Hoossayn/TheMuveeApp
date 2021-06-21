package com.example.data.repository.movies

import com.example.data.model.movies.MovieEntity

interface MovieRepository {

    suspend fun getMoviesFromRemote(language:String, page:Int): List<MovieEntity>

    suspend fun getMoviesFromLocal(): List<MovieEntity>

    suspend fun saveMovies(moviesEntity: List<MovieEntity>)

    suspend fun deleteMovies()
}