package com.vinyla_android.presentation.vinyl.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vinyla_android.databinding.ItemVinylTrackBinding
import com.vinyla_android.domain.entity.VinylTrack

class VinylTracksAdapter :
    ListAdapter<VinylTrack, VinylTracksAdapter.ViewHolder>(ItemComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemVinylTrackBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        val binding: ItemVinylTrackBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(track: VinylTrack) {
            binding.track = track
        }
    }

    private class ItemComparator : DiffUtil.ItemCallback<VinylTrack>() {
        override fun areItemsTheSame(oldItem: VinylTrack, newItem: VinylTrack): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: VinylTrack, newItem: VinylTrack): Boolean {
            return oldItem == newItem
        }
    }
}
