package com.example.data.source.remote


import com.example.data.model.Movies

interface PostDataSource

interface RemoteDataSource: PostDataSource {
    suspend fun getMovies(language:String, page: Int): Movies
}