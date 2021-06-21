package com.example.data.model.shows

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "allShows")
data class ShowsEntity(
    val backdrop_path: String,
    @PrimaryKey
    val id: Int,
    val original_language: String,
    val name: String,
    val overview: String,
)
