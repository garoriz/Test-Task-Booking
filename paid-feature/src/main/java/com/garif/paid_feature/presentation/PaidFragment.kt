package com.garif.paid_feature.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.garif.core.navigate
import com.garif.core.util.nextInt
import com.garif.paid_feature.R
import com.garif.paid_feature.databinding.FragmentPaidBinding
import com.garif.paid_feature.di.PaidFeatureComponentProvider
import java.util.Random

class PaidFragment : Fragment(R.layout.fragment_paid) {
    private lateinit var binding: FragmentPaidBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as PaidFeatureComponentProvider)
            .getPaidFeatureComponent()
            .injectPaidFragment(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPaidBinding.bind(view)

        with(binding) {
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }

            btnSuper.setOnClickListener {
                navigate(
                    R.id.action_paidFragment_to_hotelFragment,
                )
            }

            val orderDescription =
                resources.getString(R.string.order_description_start) + Random().nextInt(1..999999)
                    .toString() + " " + resources.getString(R.string.order_description_end)
            tvOrderDescription.text = orderDescription
        }
    }
}