package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.model.MovieEntity


const val DATABASE_NAME = "tmdb.db"


@Database(
    entities = [MovieEntity::class],
    version = 1,
    exportSchema = true
)

abstract class MovieDatabase: RoomDatabase(){
    abstract fun movieDao(): MovieDao
}