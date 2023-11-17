package com.garif.number_feature.presentation.adapter

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.garif.core.CustomPagerAdapter
import com.garif.core.navigate
import com.garif.core.util.moneyType
import com.garif.network.response.numbers.Room
import com.garif.number_feature.R
import com.garif.number_feature.databinding.NumberItemBinding
import com.garif.number_feature.presentation.NumbersFragment
import com.google.android.material.chip.Chip

class NumberHolder(
    private val binding: NumberItemBinding,
    private val fragment: NumbersFragment
) : RecyclerView.ViewHolder(binding.root) {
    private var number: Room? = null

    fun bind(item: Room) {
        this.number = item
        with(binding) {
            val viewPager = viewpagerPhotos
            viewPager.adapter =
                CustomPagerAdapter(fragment.requireContext(), item.image_urls)

            tlPhotos.setupWithViewPager(viewPager, true)

            tvRoomName.text = item.name
            item.peculiarities.forEach { description ->
                binding.cgPeculiarities.addView(
                    createPeculiarityChip(
                        fragment.requireContext(),
                        description
                    )
                )
            }

            tvPrice.text = item.price.toString().moneyType()
            tvPriceForIt.text = item.price_per
            btnSelectNumber.setOnClickListener {
                fragment.navigate(
                    R.id.action_numbersFragment_to_bookingFragment,
                )
            }
        }
    }

    private fun createPeculiarityChip(context: Context, chipName: String): Chip {
        return Chip(context).apply {
            text = chipName
            setChipBackgroundColorResource(com.garif.core.R.color.whiteout)
            setTextColor(ContextCompat.getColor(context, com.garif.core.R.color.silver))
            chipStrokeWidth = resources.getDimension(com.garif.core.R.dimen.x0)
            chipCornerRadius = context.resources.getDimension(com.garif.core.R.dimen.x0_5)
            isClickable = false
            setTextStartPaddingResource(com.garif.core.R.dimen.x0)
            setTextEndPaddingResource(com.garif.core.R.dimen.x0)
            chipStartPadding = resources.getDimension(com.garif.core.R.dimen.x1_25)
            chipEndPadding = resources.getDimension(com.garif.core.R.dimen.x1_25)
            setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(com.garif.core.R.dimen.size16)
            )
        }
    }

    companion object {

        fun create(
            parent: ViewGroup,
            fragment: NumbersFragment
        ) = NumberHolder(
            NumberItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), fragment
        )
    }
}