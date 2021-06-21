package com.example.home.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.core.util.Event
import com.example.core.util.convertToFlowListViewState
import com.example.core.util.convertToFlowViewState
import com.example.core.viewstate.Status
import com.example.core.viewstate.ViewState
import com.example.domain.model.Movie
import com.example.domain.usecases.GetMoviesUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

class MovieListViewModel @ViewModelInject constructor(
    private val coroutineScope: CoroutineScope,
    private val moviesUseCase: GetMoviesUseCase
): AbstractMovieListVM() {


    private val _goToDetailScreen = MutableLiveData<Event<Movie>>()
    override val gotoDetailsScreen: LiveData<Event<Movie>>
        get() = _goToDetailScreen

    private val _movieViewState = MutableLiveData<ViewState<List<Movie>>>()
    override val moviesViewState: LiveData<ViewState<List<Movie>>>
        get() = _movieViewState



    override fun getMovies() {
        moviesUseCase.getMoviesOfflineFirst()
            .convertToFlowListViewState()
            .onStart {
                _movieViewState.value = ViewState(status = Status.LOADING)
            }.onEach {
                _movieViewState.value = it
                Log.d("MoviesList", it.data.toString())
            }.launchIn(coroutineScope)
    }

    override fun refreshMovies() {
        moviesUseCase.getMoviesOfflineLast()
            .convertToFlowViewState()
            .onStart {
                _movieViewState.value = ViewState(status = Status.LOADING)
            }.onEach {
                _movieViewState.value = it
            }.launchIn(coroutineScope)
    }

    override fun onClick(movie: Movie) {
        _goToDetailScreen.value = Event(movie)
    }
}