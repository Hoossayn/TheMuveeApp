package com.example.domain.mapper

import com.example.data.mapper.ListMapper
import com.example.data.model.MovieEntity
import com.example.domain.model.Movie
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class EntityToMovie @Inject constructor(): ListMapper<MovieEntity, Movie>{
    override fun map(input: List<MovieEntity>): List<Movie> {
        return input.map {
            Movie(it.id,it.backdrop_path, it.overview, it.release_date, it.title)
        }
    }
}