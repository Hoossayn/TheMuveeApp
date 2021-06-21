package com.example.data.db.dao

import androidx.room.*
import com.example.data.model.shows.ShowsEntity

@Dao
interface ShowsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ShowsEntity):Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entities: List<ShowsEntity>): List<Long>

   /* @Delete
    suspend fun deletePost(entity: ShowsEntity): Int

    @Query("DELETE FROM allShows")
    suspend fun deleteAll()*/

    @Query("SELECT * FROM allShows")
    suspend fun getShowList(): List<ShowsEntity>
}