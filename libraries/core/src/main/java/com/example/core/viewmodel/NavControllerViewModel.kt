package com.example.core.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.core.util.Event

/**
 * ViewModel shared by Activity and fragmgents that contains the [NavController] belongs to
 * current fragment on screen.
 *
 * * Usable to change Toolbar status based on fragment currently navigated
 */
class NavControllerViewModel : ViewModel() {
    val currentNavController = MutableLiveData<Event<NavController?>>()
}