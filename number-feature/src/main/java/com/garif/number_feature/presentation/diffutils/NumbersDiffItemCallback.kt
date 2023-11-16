package com.garif.number_feature.presentation.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.garif.network.response.numbers.Room

class NumbersDiffItemCallback : DiffUtil.ItemCallback<Room>() {
    override fun areItemsTheSame(oldItem: Room, newItem: Room): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Room, newItem: Room): Boolean {
        return oldItem == newItem
    }
}