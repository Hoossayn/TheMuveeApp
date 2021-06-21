package com.example.shows.adapter

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.core.ui.adapter.BaseListAdapter
import com.example.domain.model.Shows
import com.example.shows.BR
import com.example.shows.utils.load
import kotlinx.android.synthetic.main.show_list_item.view.*


class ShowListAdapter (
    @LayoutRes private val layoutId: Int,
    private val onItemClicked: ((Shows) -> Unit)? = null
): BaseListAdapter<Shows>(
    layoutId,
    MovieDiffCallBack()
){
    override fun onViewHolderBound(
        binding: ViewDataBinding,
        item: Shows,
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
    class MovieDiffCallBack : DiffUtil.ItemCallback<Shows>() {

        override fun areItemsTheSame(
            oldItem: Shows,
            newItem: Shows
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Shows,
            newItem: Shows
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }
}