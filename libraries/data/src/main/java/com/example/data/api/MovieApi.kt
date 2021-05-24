package com.example.data.api

import com.example.data.model.Movie
import com.example.data.model.Movies
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("popular")
    suspend fun getMovies(
        @Query("language") language: String,
        @Query("page") page: Int
    ): List<Movie>

    /*@GET("{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId: Int, @Query("language") language: String): MovieDetail*/
}

const val BASE_URL = "https://api.themoviedb.org/3/"