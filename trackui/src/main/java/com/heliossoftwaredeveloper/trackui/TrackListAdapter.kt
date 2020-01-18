/* (c) Helios Software Developer. All rights reserved. */
package com.heliossoftwaredeveloper.trackui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.heliossoftwaredeveloper.trackui.databinding.TrackItemBinding
import com.heliossoftwaredeveloper.trackui.model.TrackItem

/**
 * Adapter class that display the track item
 *
 * @author Ruel N. Grajo on 01/18/2020.
 */

class TrackListAdapter(val trackListAdapterListener : TrackListAdapterListener) :
    RecyclerView.Adapter<TrackListAdapter.TrackViewHolder>() {

    private var list: List<TrackItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val binding: TrackItemBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.track_item, parent, false)
        return TrackViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.binding.item = list[position]
        holder.binding.executePendingBindings()
        holder.binding.imageLoader.setImageURI(list[position].artworkUrl)
        holder.binding.trackCardView.setOnClickListener {
            trackListAdapterListener.onTrackSelect(list[position])
        }
    }

    /**
     * Function to update the track item list data
     *
     * @param list the new set of data
     */
    fun updateDataSet(list: List<TrackItem>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class TrackViewHolder(val binding: TrackItemBinding) : RecyclerView.ViewHolder(binding.root)

    interface TrackListAdapterListener {
        fun onTrackSelect(track: TrackItem)
    }
}