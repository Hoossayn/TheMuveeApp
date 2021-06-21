package com.example.data.mapper

import com.example.data.model.movies.Movie
import com.example.data.model.movies.MovieEntity
import com.example.data.model.movies.Movies
import com.example.data.model.shows.ShowsEntity
import com.example.data.model.shows.ShowsResult

/**
 * Mapper for transforming objects between REST and database or REST/db and domain
 *  which are Non-nullable to Non-nullable
 */
interface Mapper<I, O> {
    fun map(input: I): O
}

/**
 * Mapper for transforming objects between REST and database or REST/db and domain
 * as [List] which are Non-nullable to Non-nullable
 */
interface EntityMapper<I, O> : Mapper<I, List<O>>
interface ListMapper<I,O> : Mapper<List<I>, List<O>>


class MoviesDTOtoEntityMapper : EntityMapper<Movies, MovieEntity> {

    override fun map(input: Movies): List<MovieEntity> {
        return input.results.map {
            MovieEntity(it.id, it.backdrop_path, it.overview, it.release_date, it.title, it.poster_path)
        }
    }
}

class ShowsDTOtoEntityMapper : EntityMapper<ShowsResult, ShowsEntity> {

    override fun map(input: ShowsResult): List<ShowsEntity> {
        return input.results.map {
            ShowsEntity(it.poster_path, it.id, it.original_language, it.name, it.overview)
        }
    }
}



class EntityListMapper: ListMapper<Movie, MovieEntity>{
    override fun map(input: List<Movie>): List<MovieEntity> {
        return input.map {
            MovieEntity(it.id, it.backdrop_path, it.overview, it.release_date, it.title, it.poster_path)
        }
    }

}
