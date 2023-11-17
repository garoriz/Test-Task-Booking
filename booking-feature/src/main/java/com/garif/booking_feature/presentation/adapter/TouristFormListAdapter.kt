package com.garif.booking_feature.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.garif.booking_feature.presentation.entity.TouristForm
import com.garif.booking_feature.presentation.diffutils.TouristFormDiffItemCallback
import com.google.android.material.textfield.TextInputLayout

class TouristFormListAdapter(
    private val textInputLayoutList: MutableList<TextInputLayout>
) : ListAdapter<TouristForm, TouristFormHolder>(TouristFormDiffItemCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TouristFormHolder = TouristFormHolder.create(parent, textInputLayoutList)

    override fun onBindViewHolder(holder: TouristFormHolder, position: Int) =
        holder.bind(getItem(position))

    override fun submitList(list: MutableList<TouristForm>?) {
        super.submitList(if (list == null) null else ArrayList(list))
    }
}