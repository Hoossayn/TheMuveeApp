package com.example.core.util

import androidx.navigation.dynamicfeatures.fragment.DynamicNavHostFragment
import androidx.navigation.fragment.NavHostFragment
import com.example.core.ui.fragment.navhost.FieldProperty
import com.example.core.viewmodel.NavControllerViewModel


var DynamicNavHostFragment.viewModel: NavControllerViewModel by FieldProperty {
    NavControllerViewModel()
}

var NavHostFragment.viewModel: NavControllerViewModel by FieldProperty {
    NavControllerViewModel()
}
