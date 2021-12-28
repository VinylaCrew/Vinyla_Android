package com.vinyla_android.presentation.vinyl.level

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vinyla_android.R
import com.vinyla_android.databinding.ItemVinylLevelBinding
import com.vinyla_android.domain.entity.vinyl.VinylLevel

class VinylLevelAdapter : ListAdapter<VinylLevel, VinylLevelAdapter.ViewHolder>(ItemComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemVinylLevelBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemVinylLevelBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(level: VinylLevel) {
            binding.level = level
            if (level == VinylLevel.ONE) {
                binding.root.setBackgroundResource(R.drawable.gradient_maincolor_transparent_linear)
            }
        }
    }

    private class ItemComparator : DiffUtil.ItemCallback<VinylLevel>() {
        override fun areItemsTheSame(oldItem: VinylLevel, newItem: VinylLevel): Boolean {
            return oldItem.level == newItem.level
        }

        override fun areContentsTheSame(oldItem: VinylLevel, newItem: VinylLevel): Boolean {
            return oldItem == newItem
        }
    }
}
