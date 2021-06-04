package com.vinyla_android.presentation.vinyl.collection

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vinyla_android.data.model.Vinyl
import com.vinyla_android.databinding.ItemVinylCollectionBinding

class VinylCollectionsAdapter :
    ListAdapter<Vinyl, VinylCollectionsAdapter.ViewHolder>(ItemComparator()) {

    private var onVinylClickListener: ((Vinyl) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemVinylCollectionBinding.inflate(layoutInflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemVinylCollectionBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(vinyl: Vinyl) {
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
