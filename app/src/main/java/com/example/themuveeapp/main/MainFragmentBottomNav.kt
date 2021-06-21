package com.example.themuveeapp.main

import android.os.Bundle
import android.view.View
import com.example.core.ui.fragment.DynamicNavigationFragment
import com.example.core.util.setupWithNavController
import com.example.themuveeapp.R
import com.example.themuveeapp.databinding.FragmentMainBottomNavBinding

class MainFragmentBottomNav : DynamicNavigationFragment<FragmentMainBottomNavBinding>() {

    override fun getLayoutRes(): Int = R.layout.fragment_main_bottom_nav

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        } // Else, need to wait for onRestoreInstanceState
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        // Now that BottomNavigationBar has restored its instance state
        // and its selectedItemId, we can proceed with setting up the
        // BottomNavigationBar with Navigation
        setupBottomNavigationBar()
    }

    /**
     * Called on first creation and when restoring state.
     */
    private fun setupBottomNavigationBar() {

        val bottomNavigationView = dataBinding!!.bottomNav

        val navGraphIds = listOf(
                R.navigation.nav_graph_dfm_home_start,
                R.navigation.nav_graph_dfm_shows_start,
               /* R.navigation.nav_graph_dfm_notification_start,
                R.navigation.nav_graph_dfm_account_start*/
        )

        // Setup the bottom navigation view with a list of navigation graphs
        val controller = bottomNavigationView.setupWithNavController(
                navGraphIds = navGraphIds,
                fragmentManager = childFragmentManager,
                containerId = R.id.nav_host_container,
                intent = requireActivity().intent
        )
    }
}