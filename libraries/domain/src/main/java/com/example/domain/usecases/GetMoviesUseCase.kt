package com.example.domain.usecases

import com.example.data.model.MovieEntity
import com.example.data.repository.MovieRepository
import com.example.domain.dispatcher.UseCaseDispatchers
import com.example.domain.error.EmptyDataException
import com.example.domain.mapper.EntityToMovie
import com.example.domain.model.Movie
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val repository: MovieRepository,
    private val entityToMovie: EntityToMovie,
    private val useCaseDispatchers: UseCaseDispatchers
) {

    fun getMoviesOfflineLast(): Flow<List<Movie>>{
        return flow { emit(repository.getMoviesFromRemote("en-US",1)) }
            .map {
                if (it.isNullOrEmpty()){
                    throw EmptyDataException("No Data is available in Remote source")
                }else{
                    repository.run {
                        deleteMovies()
                        saveMovies(it)
                        getMoviesFromLocal()
                    }
                }
            }.flowOn(useCaseDispatchers.ioDispatcher)
            .catch { throwable ->  emitAll(flowOf(repository.getMoviesFromLocal())) }
            .map { toPostListOrError(it) }
    }


    fun getMoviesOfflineFirst(): Flow<List<Movie>>{
        return flow { emit(repository.getMoviesFromLocal()) }
            .catch { throwable ->
                emitAll(flowOf(listOf()))
            }.map {
                if (it.isEmpty()){
                    repository.run {
                        val result = getMoviesFromRemote("en-US",1)
                        deleteMovies()
                        saveMovies(result)
                        result
                    }
                }else{
                    it
                }
            }.flowOn(useCaseDispatchers.ioDispatcher)
            .catch { throwable ->
                emitAll(flowOf(listOf()))
            }.map {
                toPostListOrError(it)
            }
    }



    private fun toPostListOrError(postEntityList: List<MovieEntity>): List<Movie> {
        return if (!postEntityList.isNullOrEmpty()) {
            entityToMovie.map(postEntityList)
        } else {
            throw EmptyDataException("Empty data mapping error!")
        }
    }
}