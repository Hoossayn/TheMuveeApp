package com.example.home.di

import androidx.fragment.app.Fragment
import com.example.core.di.CoreModuleDependencies
import com.example.home.movieList.MovieListFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [CoreModuleDependencies::class],
    modules = [MovieListModule::class]
)
interface MovieListComponent {
    fun inject(movieListFragment: MovieListFragment)

    @Component.Factory
    interface Factory{
        fun create(
            dependentModule: CoreModuleDependencies,
            @BindsInstance fragment:Fragment
        ):MovieListComponent
    }
}