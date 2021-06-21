package com.example.home.adapter

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.core.ui.adapter.BaseListAdapter
import com.example.domain.model.Movie
import com.example.home.BR
import com.example.home.utils.load
import kotlinx.android.synthetic.main.movies_list_item.view.*

class MovieListAdapter (
    @LayoutRes private val layoutId: Int,
    private val onItemClicked: ((Movie) -> Unit)? = null
): BaseListAdapter<Movie>(
    layoutId,
    MovieDiffCallBack()
){
    override fun onViewHolderBound(
        binding: ViewDataBinding,
        item: Movie,
        position: Int,
        payloads: MutableList<Any>
    ) {
        binding.setVariable(BR.item, item)
        binding.root.coverImageView.load(item.poster_path)
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

    /**
     * Callback for calculating the diff between two non-null items in a list.
     *
     * Used by ListAdapter to calculate the minimum number of changes between and old list and a new
     * list that's been passed to `submitList`.
     */
    class MovieDiffCallBack : DiffUtil.ItemCallback<Movie>() {

        override fun areItemsTheSame(
            oldItem: Movie,
            newItem: Movie
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Movie,
            newItem: Movie
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }
}