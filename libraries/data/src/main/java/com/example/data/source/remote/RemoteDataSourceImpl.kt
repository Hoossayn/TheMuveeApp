package com.example.data.source.remote

import com.example.data.api.MovieApi
import com.example.data.model.Movie
import retrofit2.http.Query
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val movieApi: MovieApi
):RemoteDataSource{

    override suspend fun getMovies(language: String, page: Int): List<Movie> {
        return movieApi.getMovies(language,page)
    }


}