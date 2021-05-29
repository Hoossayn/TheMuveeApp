package com.example.data.source.remote

import com.example.data.api.MovieApi
import com.example.data.model.Movie
import com.example.data.model.Movies
import retrofit2.http.Query
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val movieApi: MovieApi
):RemoteDataSource{

    override suspend fun getMovies(language: String, page: Int): Movies {
        return movieApi.getMovies(language,page)
    }


}