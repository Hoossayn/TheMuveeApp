package com.example.shows.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecases.GetMoviesUseCase
import com.example.domain.usecases.GetShowsUseCase
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject


class ShowListViewModelFactory @Inject constructor(
    private val coroutineScope: CoroutineScope,
    private val getPostsUseCase: GetShowsUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass != ShowsListViewModel::class.java) {
            throw IllegalArgumentException("Unknown ViewModel class")
        }

        return ShowsListViewModel(
            coroutineScope,
            getPostsUseCase
        ) as T
    }
}