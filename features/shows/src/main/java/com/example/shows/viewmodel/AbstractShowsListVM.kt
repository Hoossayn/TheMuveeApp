package com.example.shows.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.core.util.Event
import com.example.core.viewstate.ViewState
import com.example.domain.model.Shows

abstract class AbstractShowsListVM: ViewModel() {

    abstract val gotoDetailsScreen: LiveData<Event<Shows>>

    abstract val showsViewState: LiveData<ViewState<List<Shows>>>

    abstract fun getShows()

    abstract fun refreshShows()

    abstract fun onClick(shows: Shows)
}