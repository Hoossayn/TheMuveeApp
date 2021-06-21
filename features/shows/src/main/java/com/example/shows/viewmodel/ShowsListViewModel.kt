package com.example.shows.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.core.util.Event
import com.example.core.util.convertToFlowListViewState
import com.example.core.util.convertToFlowViewState
import com.example.core.viewstate.Status
import com.example.core.viewstate.ViewState
import com.example.domain.model.Shows
import com.example.domain.usecases.GetShowsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

class ShowsListViewModel @ViewModelInject constructor(
    private val coroutineScope: CoroutineScope,
    private val showsUseCase: GetShowsUseCase
): AbstractShowsListVM() {

    private val _goToDetailScreen = MutableLiveData<Event<Shows>>()
    override val gotoDetailsScreen: LiveData<Event<Shows>>
        get() = _goToDetailScreen

    private val _showsViewState = MutableLiveData<ViewState<List<Shows>>>()
    override val showsViewState: LiveData<ViewState<List<Shows>>>
        get() = _showsViewState



    override fun getShows() {
        showsUseCase.getShowsOfflineFirst()
            .convertToFlowListViewState()
            .onStart {
                _showsViewState.value = ViewState(status = Status.LOADING)
            }.onEach {
                _showsViewState.value = it
            }.launchIn(coroutineScope)
    }

    override fun refreshShows() {
        showsUseCase.getShowsOfflineLast()
            .convertToFlowViewState()
            .onStart {
                _showsViewState.value = ViewState(status = Status.LOADING)
            }.onEach {
                _showsViewState.value = it
            }.launchIn(coroutineScope)
    }

    override fun onClick(shows: Shows) {
        _goToDetailScreen.value = Event(shows)
    }
}