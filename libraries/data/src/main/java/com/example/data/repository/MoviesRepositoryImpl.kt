package com.example.data.repository

import android.util.Log
import com.example.data.model.MovieEntity
import com.example.data.model.Movies
import com.example.data.source.local.LocalMoviesDataSource
import com.example.data.source.remote.RemoteDataSource
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val localDataSource: LocalMoviesDataSource,
    private val remoteDataSource: RemoteDataSource
  //  private val mapper: DTOtoEntityMapper
):MovieRepository{

    override suspend fun getMoviesFromRemote(language:String, page:Int): List<MovieEntity> {
      //  val ss = remoteDataSource.getMovies(language,page)
        Log.d("getMovieList", remoteDataSource.getMovies(language, page).toString())
        val movieEntities = mutableListOf<MovieEntity>()
        return remoteDataSource.getMovies(language,page).results.mapTo(movieEntities){
            MovieEntity(id = it.id, backdrop_path = it.backdrop_path, overview = it.overview, release_date = it.release_date, title = it.title)
        }
    }



    override suspend fun getMoviesFromLocal(): List<MovieEntity> {
        return localDataSource.getMoviesEntities()
    }

    override suspend fun saveMovies(moviesEntity: List<MovieEntity>) {
        localDataSource.saveEntities(moviesEntity)
    }

    override suspend fun deleteMovies() {
        localDataSource.deletePostEntities()
    }

}