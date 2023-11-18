package com.garif.booking_feature.presentation.adapter

import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.garif.booking_feature.R
import com.garif.booking_feature.databinding.TouristFormItemBinding
import com.garif.booking_feature.presentation.entity.TouristForm
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class TouristFormHolder(
    private val binding: TouristFormItemBinding,
    private val textInputLayoutList: MutableList<TextInputLayout>
) : RecyclerView.ViewHolder(binding.root) {
    private var touristForm: TouristForm? = null

    fun bind(item: TouristForm) {
        this.touristForm = item
        with(binding) {
            //tvFirstTourist.text = item.touristFormName
            btnArrow.setOnClickListener {
                if (hiddenView.isVisible) {
                    TransitionManager.beginDelayedTransition(cvFirstTourist, AutoTransition())
                    hiddenView.visibility = View.GONE
                    btnArrow.setImageResource(R.drawable.ic_arrow_down)
                } else {
                    TransitionManager.beginDelayedTransition(cvFirstTourist, AutoTransition())
                    hiddenView.visibility = View.VISIBLE
                    btnArrow.setImageResource(R.drawable.ic_arrow_up)
                }
            }

            addTextInputLayouts()
        }
    }

    private fun addTextInputLayouts() {
        with(binding) {
            textInputLayoutList.add(tilName)
            textInputLayoutList.add(tilSurname)
            textInputLayoutList.add(tilBirthday)
            textInputLayoutList.add(tilCitizenship)
            textInputLayoutList.add(tilPassport)
            textInputLayoutList.add(tilPassportValidityPeriod)
        }
    }

    companion object {

        fun create(
            parent: ViewGroup,
            textInputLayoutList: MutableList<TextInputLayout>
        ) = TouristFormHolder(
            TouristFormItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), textInputLayoutList
        )
    }
}