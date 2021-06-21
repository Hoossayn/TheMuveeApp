package com.example.shows.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.shows.viewmodel.ShowListViewModelFactory
import com.example.shows.viewmodel.ShowsListViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

@InstallIn(FragmentComponent::class)
@Module
class ShowListModule {
    @Provides
    fun providesMovieListViewModel(fragment: Fragment, factory: ShowListViewModelFactory) =
        ViewModelProvider(fragment, factory).get(ShowsListViewModel::class.java)

    @Provides
    fun provideCoroutineScope() = CoroutineScope(Dispatchers.Main.immediate + SupervisorJob())
}