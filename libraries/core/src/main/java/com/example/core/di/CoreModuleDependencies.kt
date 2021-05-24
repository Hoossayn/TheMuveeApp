package com.example.core.di

import com.example.domain.usecases.GetMoviesUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@EntryPoint
@InstallIn(ApplicationComponent::class)
interface CoreModuleDependencies {


    fun getMovieList(): GetMoviesUseCase
}