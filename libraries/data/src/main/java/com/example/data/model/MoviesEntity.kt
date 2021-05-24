package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "allMovies")
data class MovieEntity(
   /* val adult: Boolean,

    val genre_ids: List<Int>,*/
    @PrimaryKey
    val id: Int,
    val backdrop_path: String,
 /*   val original_language: String,
    val original_title: String,*/
   /*
    val popularity: Double,
    val poster_path: String,*/
    val overview: String,
    val release_date: String,
    val title: String,
  /*  val video: Boolean,
    val vote_average: Double,
    val vote_count: Int*/
)