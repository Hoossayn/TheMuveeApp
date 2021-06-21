package com.example.shows.utils

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Movie
import com.example.domain.model.Shows

/**
 * [BindingAdapter]s for the [Post]s to ListAdapter.
 */
@BindingAdapter("app:items")
fun RecyclerView.setItems(items: List<Shows>?) {

    items?.let {
        (adapter as ListAdapter<Shows, *>)?.submitList(items)
    }
}


/**
 * Display or hide a view based on a condition
 */
@BindingAdapter("visibilityBasedOn")
fun View.visibilityBasedOn(condition: Boolean) {
    visibility = if (condition) View.VISIBLE else View.GONE
}

