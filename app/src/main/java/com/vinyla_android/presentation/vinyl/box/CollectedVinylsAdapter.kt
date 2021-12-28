package com.vinyla_android.presentation.vinyl.box

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vinyla_android.databinding.ItemCollectedVinylBinding
import com.vinyla_android.domain.entity.vinyl.Vinyl

class CollectedVinylsAdapter :
    ListAdapter<Vinyl, CollectedVinylsAdapter.CollectedVinylViewHolder>(ItemComparator()) {

    private var onVinylClickListener: ((Vinyl) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectedVinylViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCollectedVinylBinding.inflate(layoutInflater, parent, false)
        return CollectedVinylViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CollectedVinylViewHolder, position: Int) {
        holder.bind(getItem(position), onVinylClickListener)
    }

    fun setOnVinylClickListener(listener: ((Vinyl) -> Unit)?) {
        this.onVinylClickListener = listener
    }

    class CollectedVinylViewHolder(
        private val binding: ItemCollectedVinylBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(vinyl: Vinyl, onVinylClickListener: ((Vinyl) -> Unit)?) {
            binding.vinly = vinyl
            binding.root.setOnClickListener { onVinylClickListener?.invoke(vinyl) }
        }
    }

    private class ItemComparator : DiffUtil.ItemCallback<Vinyl>() {

        override fun areItemsTheSame(oldItem: Vinyl, newItem: Vinyl): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Vinyl, newItem: Vinyl): Boolean {
            return oldItem == newItem
        }
    }
}
