package com.garif.number_feature.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.garif.core.CustomPagerAdapter
import com.garif.network.response.numbers.Room
import com.garif.number_feature.databinding.NumberItemBinding

class NumberHolder (
    private val binding: NumberItemBinding,
    private val action: () -> Unit,
    private val context: Context
) : RecyclerView.ViewHolder(binding.root) {
    private var number: Room? = null

    init {
        itemView.setOnClickListener {
            action
        }
    }

    fun bind(item: Room) {
        this.number = item
        with(binding) {
            val viewPager = viewpagerPhotos
            viewPager.adapter =
                CustomPagerAdapter(context, item.image_urls)

            tlPhotos.setupWithViewPager(viewPager, true)

            tvRoomName.text = item.name
        }
    }

    companion object {

        fun create(
            parent: ViewGroup,
            action: () -> Unit,
            context: Context
        ) = NumberHolder(
            NumberItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), action, context
        )
    }
}