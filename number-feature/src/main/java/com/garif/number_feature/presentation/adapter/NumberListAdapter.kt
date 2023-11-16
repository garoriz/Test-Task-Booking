package com.garif.number_feature.presentation.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.garif.network.response.numbers.Room
import com.garif.number_feature.presentation.diffutils.NumbersDiffItemCallback

class NumberListAdapter(
    private val context: Context,
    private val action: () -> Unit
) : ListAdapter<Room, NumberHolder>(NumbersDiffItemCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NumberHolder = NumberHolder.create(parent, action, context)

    override fun onBindViewHolder(holder: NumberHolder, position: Int) =
        holder.bind(getItem(position))

    override fun submitList(list: MutableList<Room>?) {
        super.submitList(if (list == null) null else ArrayList(list))
    }
}