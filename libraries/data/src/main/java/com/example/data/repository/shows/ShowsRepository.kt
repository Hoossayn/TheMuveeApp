package com.example.data.repository.shows

import com.example.data.model.shows.ShowsEntity

interface ShowsRepository {

    suspend fun getShowsFromRemote(language:String, page:Int): List<ShowsEntity>

    suspend fun getShowsFromLocal(): List<ShowsEntity>

    suspend fun saveShows(showsEntity: List<ShowsEntity>)

    suspend fun deleteShows()
}



