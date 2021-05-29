package com.example.data.mapper

import com.example.data.model.Movie
import com.example.data.model.MovieEntity
import com.example.data.model.Movies

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
interface ListMapper<I, O> : Mapper<List<I>, List<O>>


/*
class DTOtoEntityMapper : ListMapper<Movies, MovieEntity> {

    override fun map(input: Movies): List<MovieEntity> {
        return input.map {
            MovieEntity(it, it.backdrop_path, it.overview, it.release_date, it.title)
        }
    }
}*/
