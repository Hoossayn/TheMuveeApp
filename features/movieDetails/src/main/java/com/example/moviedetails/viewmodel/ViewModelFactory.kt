package com.example.moviedetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject


class MovieDetailViewModelFactory @Inject constructor(
    private val coroutineScope: CoroutineScope
  //  private val getPostsUseCase: GetPostsWithStatusUseCaseFlow
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass != MovieDetailsViewModel::class.java) {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
        return MovieDetailsViewModel(
            coroutineScope,
           // getPostsUseCase
        ) as T
    }
}
