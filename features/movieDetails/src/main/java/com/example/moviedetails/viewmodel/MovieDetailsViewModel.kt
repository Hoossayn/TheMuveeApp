package com.example.moviedetails.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(
    private val coroutineScope: CoroutineScope,
):ViewModel() {
}