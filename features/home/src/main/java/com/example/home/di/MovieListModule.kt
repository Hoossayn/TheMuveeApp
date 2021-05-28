package com.example.home.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.home.viewmodel.MovieListViewModel
import com.example.home.viewmodel.MovieListViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

@InstallIn(FragmentComponent::class)
@Module
class MovieListModule {
    @Provides
    fun providesMovieListViewModel(fragment: Fragment, factory: MovieListViewModelFactory) =
        ViewModelProvider(fragment, factory).get(MovieListViewModel::class.java)

    @Provides
    fun provideCoroutineScope() = CoroutineScope(Dispatchers.Main.immediate + SupervisorJob())
}