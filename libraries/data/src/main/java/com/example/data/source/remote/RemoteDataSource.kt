package com.example.data.source.remote

import com.example.data.model.Movie

interface RemoteDataSource {
    suspend fun getMovies(
        language:String,
        page: Int
    ): List<Movie>
}