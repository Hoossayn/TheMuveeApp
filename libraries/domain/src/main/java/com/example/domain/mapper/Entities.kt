package com.example.domain.mapper

import com.example.data.mapper.ListMapper
import com.example.data.model.movies.MovieEntity
import com.example.data.model.shows.ShowsEntity
import com.example.domain.model.Movie
import com.example.domain.model.Shows
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class EntityToMovie @Inject constructor(): ListMapper<MovieEntity, Movie>{
    override fun map(input: List<MovieEntity>): List<Movie> {
        return input.map {
            Movie(it.id,it.backdrop_path, it.overview, it.release_date, it.title, it.poster_path)
        }
    }
}

@Singleton
class EntityToShows @Inject constructor(): ListMapper<ShowsEntity, Shows>{
    override fun map(input: List<ShowsEntity>): List<Shows> {
        return input.map {
            Shows(it.backdrop_path, it.id, it.original_language, it.name, it.overview)
        }
    }
}