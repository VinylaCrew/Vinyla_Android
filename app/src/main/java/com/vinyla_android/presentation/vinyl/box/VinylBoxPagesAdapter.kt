package com.vinyla_android.presentation.vinyl.box

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vinyla_android.data.model.Vinyls
import com.vinyla_android.databinding.ItemVinylBoxPageBinding

/**
 * Created By Malibin
 * on 7월 28, 2021
 */

class VinylBoxPagesAdapter :
    ListAdapter<Vinyls, VinylBoxPagesAdapter.VinylBoxPageViewHolder>(ItemComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VinylBoxPageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemVinylBoxPageBinding.inflate(layoutInflater)
        return VinylBoxPageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VinylBoxPageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class VinylBoxPageViewHolder(
        private val binding: ItemVinylBoxPageBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(vinyls: Vinyls) {
            val collectedVinylsAdapter = CollectedVinylsAdapter()
            binding.listVinyls.adapter = collectedVinylsAdapter
            collectedVinylsAdapter.submitList(vinyls.get())
        }
    }

    private class ItemComparator : DiffUtil.ItemCallback<Vinyls>() {

        override fun areItemsTheSame(oldItem: Vinyls, newItem: Vinyls): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Vinyls, newItem: Vinyls): Boolean {
            return oldItem == newItem
        }
    }
}
