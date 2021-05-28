package com.example.themuveeapp.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import com.example.core.ui.adapter.NavigableFragmentStateAdapter
import com.example.core.ui.fragment.navhost.BaseDynamicNavHostFragment
import com.example.core.ui.fragment.navhost.BaseNavHostFragment
import com.example.themuveeapp.R


/**
 * ViewPager2 Adapter for changing tabs of BottomNavigationView with [BaseDynamicNavHostFragment]
 * which are extension of [DynamicNavHostFragment] that let's fragments from Dynamic Feature
 * Modules to be added as root of [BottomNavigationView]
 *
 */

class DynamicBottomNavigationStateAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    NavigableFragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {

            // Home nav graph
            0 ->
                BaseNavHostFragment
                    .createNavHostFragment(R.navigation.nav_graph_dfm_home_start)

            // Dashboard nav graph
            1 ->
                BaseDynamicNavHostFragment
                    .createDynamicNavHostFragment(R.navigation.nav_graph_dfm_dashboard_start)

            // Notification nav graph
            2 ->
                BaseNavHostFragment
                    .createNavHostFragment(R.navigation.nav_graph_dfm_notification_start)

            // Account nav graph
            else ->
                BaseNavHostFragment
                    .createNavHostFragment(R.navigation.nav_graph_dfm_account_start)
        }
    }
}
