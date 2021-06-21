package com.example.data.repository.shows

import com.example.data.mapper.ShowsDTOtoEntityMapper
import com.example.data.model.shows.ShowsEntity
import com.example.data.source.local.LocalMoviesDataSource
import com.example.data.source.remote.RemoteDataSource
import javax.inject.Inject

class ShowsRepositoryImpl @Inject constructor(
    private val localDataSource: LocalMoviesDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val mapperMovies: ShowsDTOtoEntityMapper
):ShowsRepository{

    override suspend fun getShowsFromRemote(language:String, page:Int): List<ShowsEntity> {
        val showsDTOList = remoteDataSource.getShows(language,page)
        return mapperMovies.map(showsDTOList)
    }
    override suspend fun getShowsFromLocal(): List<ShowsEntity> {
        return localDataSource.getShowsEntities()
    }

    override suspend fun saveShows(moviesEntity: List<ShowsEntity>) {
        localDataSource.saveShowsEntities(moviesEntity)
    }

    override suspend fun deleteShows() {
        localDataSource.deletePostEntities()
    }

}