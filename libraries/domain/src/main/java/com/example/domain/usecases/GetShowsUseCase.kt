package com.example.domain.usecases

import com.example.data.model.shows.ShowsEntity
import com.example.data.repository.shows.ShowsRepository
import com.example.domain.dispatcher.UseCaseDispatchers
import com.example.domain.error.EmptyDataException
import com.example.domain.mapper.EntityToShows
import com.example.domain.model.Shows
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetShowsUseCase @Inject constructor(
    private val repository: ShowsRepository,
    private val entityToMovie: EntityToShows,
    private val useCaseDispatchers: UseCaseDispatchers
) {

    fun getShowsOfflineLast(): Flow<List<Shows>>{
        return flow { emit(repository.getShowsFromRemote("en-US",1)) }
            .map {
                if (it.isNullOrEmpty()){
                    throw EmptyDataException("No Data is available in Remote source")
                }else{
                    repository.run {
                        deleteShows()
                        saveShows(it)
                        getShowsFromLocal()
                    }
                }
            }.flowOn(useCaseDispatchers.ioDispatcher)
            .catch { throwable ->  emitAll(flowOf(repository.getShowsFromLocal())) }
            .map { toPostListOrError(it) }
    }


    fun getShowsOfflineFirst(): Flow<List<Shows>>{
        return flow { emit(repository.getShowsFromLocal()) }
            .catch { throwable ->
                emitAll(flowOf(listOf()))
            }.map {
                if (it.isEmpty()){
                    repository.run {
                        val result = getShowsFromRemote("en-US",1)
                        deleteShows()
                        saveShows(result)
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



    private fun toPostListOrError(postEntityList: List<ShowsEntity>): List<Shows> {
        return if (!postEntityList.isNullOrEmpty()) {
            entityToMovie.map(postEntityList)
        } else {
            throw EmptyDataException("Empty data mapping error!")
        }
    }
}