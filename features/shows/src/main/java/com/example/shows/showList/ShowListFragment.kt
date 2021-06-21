package com.example.shows.showList

import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.core.di.CoreModuleDependencies
import com.example.core.ui.fragment.DynamicNavigationFragment
import com.example.shows.R
import com.example.shows.adapter.ShowListAdapter
import com.example.shows.databinding.FragmentShowListBinding
import com.example.shows.di.DaggerShowListComponent
import com.example.shows.viewmodel.ShowListViewModelFactory
import com.example.shows.viewmodel.ShowsListViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject


class ShowListFragment: DynamicNavigationFragment<FragmentShowListBinding>(){

    @Inject
    lateinit var viewModel: ShowsListViewModel

    override fun getLayoutRes(): Int = R.layout.fragment_show_list

    override fun onCreate(savedInstanceState: Bundle?) {
        initCoreDependentInjection()
        super.onCreate(savedInstanceState)
        viewModel.getShows()
    }

    override fun bindViews(view: View, savedInstanceState: Bundle?) {
        dataBinding.viewModel = viewModel

        dataBinding.recyclerView.apply {
            this.layoutManager = GridLayoutManager(requireContext(), 3)

            this.adapter = ShowListAdapter(R.layout.show_list_item, viewModel::onClick)
        }

        val swipeRefreshLayout = dataBinding.swipeRefreshLayout

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            viewModel.refreshShows()
        }

        subscribeGoToDetailScreen()
    }


    private fun subscribeGoToDetailScreen(){

        viewModel.gotoDetailsScreen.observe(
            viewLifecycleOwner,{
                it.getContentIfNotHandled()?.let { movie ->
                    val bundle = bundleOf("movie" to movie)

                   /* findNavController().navigate(
                        R.id.action_movieListFragment_to_nav_graph_movie_detail, bundle
                    )*/
                }
            }
        )
    }


    private fun initCoreDependentInjection() {

        val coreModuleDependencies = EntryPointAccessors.fromApplication(
            requireActivity().applicationContext,
            CoreModuleDependencies::class.java
        )

        DaggerShowListComponent.factory().create(
            dependentModule = coreModuleDependencies,
            fragment = this
        )
            .inject(this)
    }


}