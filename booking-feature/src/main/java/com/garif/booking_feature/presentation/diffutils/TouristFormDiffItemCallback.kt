package com.garif.booking_feature.presentation.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.garif.booking_feature.presentation.entity.TouristForm

class TouristFormDiffItemCallback : DiffUtil.ItemCallback<TouristForm>() {
    override fun areItemsTheSame(oldItem: TouristForm, newItem: TouristForm): Boolean =
        oldItem.touristFormName == newItem.touristFormName

    override fun areContentsTheSame(oldItem: TouristForm, newItem: TouristForm): Boolean {
        return oldItem == newItem
    }
}