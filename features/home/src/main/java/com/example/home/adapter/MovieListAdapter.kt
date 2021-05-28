package com.example.home.adapter

import android.widget.BaseAdapter
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.core.ui.adapter.BaseListAdapter
import com.example.domain.model.Movie
import com.example.home.BR
import com.example.home.databinding.MoviesListItemBinding

class MovieListAdapter (
    @LayoutRes private val layoutId: Int,
    private val onItemClicked: ((Movie) -> Unit)? = null
): BaseListAdapter<Movie>(
    layoutId,
   // MovieDiffCallBack()
){
    override fun onViewHolderBound(
        binding: ViewDataBinding,
        item: Movie,
        position: Int,
        payloads: MutableList<Any>
    ) {
        binding.setVariable(BR._all, item)
    }

    override fun onViewHolderCreated(
        viewHolder: RecyclerView.ViewHolder,
        viewType: Int,
        binding: ViewDataBinding
    ) {
        binding.root.setOnClickListener {
            onItemClicked?.let {
                it((getItem(viewHolder.bindingAdapterPosition)))
            }
        }

      //  if (binding is MoviesListItemBinding)
    }
}