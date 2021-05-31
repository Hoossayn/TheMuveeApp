package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "allMovies")
data class MovieEntity(
    @PrimaryKey
    val id: Int,
    val backdrop_path: String,
    val overview: String,
    val release_date: String,
    val title: String,
    val poster_path: String,
)