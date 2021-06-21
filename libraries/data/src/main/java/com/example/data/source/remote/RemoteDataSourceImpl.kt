package com.example.data.source.remote

import com.example.data.api.MovieApi
import com.example.data.model.movies.Movies
import com.example.data.model.movies.Movie
import com.example.data.model.shows.Shows
import com.example.data.model.shows.ShowsResult
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val movieApi: MovieApi
):RemoteDataSource{

    override suspend fun getMovies(language: String, page: Int): Movies {
        return movieApi.getMovies(language,page)
    }

    override suspend fun getShows(language: String, page: Int): ShowsResult {
        return movieApi.getShows(language, page)
    }


}