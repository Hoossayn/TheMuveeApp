package com.example.data.db

import androidx.room.*
import com.example.data.model.Movie
import com.example.data.model.MovieEntity

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: MovieEntity):Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entities: List<MovieEntity>): List<Long>

    @Delete
    suspend fun deletePost(entity: MovieEntity): Int

    @Query("DELETE FROM allMovies")
    suspend fun deleteAll()

    @Query("SELECT * FROM allMovies")
    suspend fun getMovieList(): List<MovieEntity>
}