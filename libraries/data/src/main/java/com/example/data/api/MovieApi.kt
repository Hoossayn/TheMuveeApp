package com.example.data.api

import com.example.data.model.movies.Movies
import com.example.data.model.movies.Movie
import com.example.data.model.shows.Shows
import com.example.data.model.shows.ShowsResult
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular")
    suspend fun getMovies(
        @Query("language") language: String,
        @Query("page") page: Int
    ): Movies


    @GET("tv/popular")
    suspend fun getShows(
        @Query("language") language: String,
        @Query("page") page: Int
    ): ShowsResult

    /*@GET("{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId: Int, @Query("language") language: String): MovieDetail*/
}

const val BASE_URL = "https://api.themoviedb.org/3/"
const val API_KEY = "b90d08104f6ddf9bb30c704bccac4f6a"