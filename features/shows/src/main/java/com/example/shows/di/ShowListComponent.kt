package com.example.shows.di

import androidx.fragment.app.Fragment
import com.example.core.di.CoreModuleDependencies
import com.example.shows.showList.ShowListFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [CoreModuleDependencies::class],
    modules = [ShowListModule::class]
)
interface ShowListComponent {
    fun inject(showListFragment: ShowListFragment)

    @Component.Factory
    interface Factory{
        fun create(
            dependentModule: CoreModuleDependencies,
            @BindsInstance fragment:Fragment
        ):ShowListComponent
    }
}