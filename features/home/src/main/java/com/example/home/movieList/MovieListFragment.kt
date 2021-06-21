package com.example.home.movieList

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.core.di.CoreModuleDependencies
import com.example.core.ui.fragment.DynamicNavigationFragment
import com.example.home.R
import com.example.home.adapter.MovieListAdapter
import com.example.home.databinding.FragmentMovieListBinding
import com.example.home.di.DaggerMovieListComponent
import com.example.home.viewmodel.MovieListViewModel
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject


class MovieListFragment : DynamicNavigationFragment<FragmentMovieListBinding>() {


    @Inject
    lateinit var viewModel: MovieListViewModel

    override fun getLayoutRes(): Int  = R.layout.fragment_movie_list

    override fun onCreate(savedInstanceState: Bundle?) {
        initCoreDependentInjection()
        super.onCreate(savedInstanceState)
        viewModel.getMovies()
    }


    override fun bindViews(view: View, savedInstanceState: Bundle?) {
        dataBinding.viewModel = viewModel

        dataBinding.recyclerView.apply {
            this.layoutManager = GridLayoutManager(requireContext(), 3)

            this.adapter = MovieListAdapter(R.layout.movies_list_item, viewModel::onClick)

        }


        val swipeRefreshLayout = dataBinding.swipeRefreshLayout

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            viewModel.refreshMovies()
        }

        subscribeGoToDetailScreen()
    }


    private fun subscribeGoToDetailScreen(){

        viewModel.gotoDetailsScreen.observe(
            viewLifecycleOwner,{
                it.getContentIfNotHandled()?.let { movie ->
                    val bundle = bundleOf("movie" to movie)

                    findNavController().navigate(
                        R.id.action_movieListFragment_to_nav_graph_movie_detail, bundle
                    )
                }
            }
        )
    }


    private fun initCoreDependentInjection() {

        val coreModuleDependencies = EntryPointAccessors.fromApplication(
            requireActivity().applicationContext,
            CoreModuleDependencies::class.java
        )

        DaggerMovieListComponent.factory().create(
            dependentModule = coreModuleDependencies,
            fragment = this
        )
            .inject(this)
    }


}