package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.db.dao.MovieDao
import com.example.data.db.dao.ShowsDao
import com.example.data.model.movies.MovieEntity
import com.example.data.model.shows.ShowsEntity


const val DATABASE_NAME = "tmdb.db"


@Database(
    entities = [MovieEntity::class, ShowsEntity::class],
    version = 1,
    exportSchema = true
)

abstract class MovieDatabase: RoomDatabase(){
    abstract fun movieDao(): MovieDao
    abstract fun showsDao(): ShowsDao
}