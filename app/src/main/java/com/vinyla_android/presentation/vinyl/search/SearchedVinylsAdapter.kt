package com.vinyla_android.presentation.vinyl.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vinyla_android.databinding.ItemVinylSearchedBinding
import com.vinyla_android.domain.entity.SimpleVinyl

/**
 * Created By Malibin
 * on 10월 11, 2021
 */

class SearchedVinylsAdapter :
    ListAdapter<SimpleVinyl, SearchedVinylsAdapter.ViewHolder>(ItemComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemVinylSearchedBinding.inflate(layoutInflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemVinylSearchedBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(simpleVinyl: SimpleVinyl) {
            binding.vinyl = simpleVinyl
        }
    }

    private class ItemComparator : DiffUtil.ItemCallback<SimpleVinyl>() {
        override fun areItemsTheSame(oldItem: SimpleVinyl, newItem: SimpleVinyl): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SimpleVinyl, newItem: SimpleVinyl): Boolean {
            return oldItem == newItem
        }
    }
}
