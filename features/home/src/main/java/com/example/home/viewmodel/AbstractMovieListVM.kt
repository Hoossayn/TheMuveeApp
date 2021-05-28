package com.example.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.core.util.Event
import com.example.core.viewstate.ViewState
import com.example.domain.model.Movie

abstract class AbstractMovieListVM: ViewModel() {

    abstract val gotoDetailsScreen: LiveData<Event<Movie>>

    abstract val moviesViewState: LiveData<ViewState<List<Movie>>>

    abstract fun getMovies()

    abstract fun refreshMovies()

    abstract fun onClick(movie: Movie)
}