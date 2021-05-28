package com.example.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecases.GetMoviesUseCase
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject


class MovieListViewModelFactory @Inject constructor(
    private val coroutineScope: CoroutineScope,
    private val getPostsUseCase: GetMoviesUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass != MovieListViewModel::class.java) {
            throw IllegalArgumentException("Unknown ViewModel class")
        }

        return MovieListViewModel(
            coroutineScope,
            getPostsUseCase
        ) as T
    }
}