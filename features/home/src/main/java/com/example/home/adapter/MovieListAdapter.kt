package com.example.home.adapter

import android.widget.BaseAdapter
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.core.ui.adapter.BaseListAdapter
import com.example.domain.model.Movie
import com.example.home.BR
import com.example.home.databinding.MoviesListItemBinding
import com.example.home.utils.load
import kotlinx.android.synthetic.main.movies_list_item.view.*

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
        binding.setVariable(BR.item, item)
        binding.root.coverImageView.load(item.backdrop_path)
    }

    override fun onViewHolderCreated(
        viewHolder: RecyclerView.ViewHolder,
        viewType: Int,
        binding: ViewDataBinding
    ) {

       // binding.root.coverImageView.load("",viewHolder.itemView)

        binding.root.setOnClickListener {
            onItemClicked?.let {
                it((getItem(viewHolder.bindingAdapterPosition)))
            }
        }

      //  if (binding is MoviesListItemBinding)
    }
}