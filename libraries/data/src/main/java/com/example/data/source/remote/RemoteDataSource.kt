package com.example.data.source.remote


import com.example.data.model.movies.Movies
import com.example.data.model.movies.Movie
import com.example.data.model.shows.Shows
import com.example.data.model.shows.ShowsResult

interface PostDataSource

interface RemoteDataSource: PostDataSource {
    suspend fun getMovies(language:String, page: Int): Movies
    suspend fun getShows(language:String, page: Int): ShowsResult
}