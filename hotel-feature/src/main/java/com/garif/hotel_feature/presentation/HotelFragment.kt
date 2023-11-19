package com.garif.hotel_feature.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.garif.core.navigate
import com.garif.core.util.AppViewModelFactory
import com.garif.core.util.moneyType
import com.garif.hotel_feature.R
import com.garif.hotel_feature.databinding.FragmentHotelBinding
import com.garif.hotel_feature.di.HotelFeatureComponentProvider
import com.garif.hotel_feature.presentation.adapter.CustomPagerAdapter
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject


class HotelFragment : Fragment(R.layout.fragment_hotel) {
    @Inject
    lateinit var factory: AppViewModelFactory
    private lateinit var binding: FragmentHotelBinding
    private val viewModel: HotelViewModel by viewModels {
        factory
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as HotelFeatureComponentProvider)
            .getHotelFeatureComponent()
            .injectHotelFragment(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHotelBinding.bind(view)

        initObservers()
        viewModel.onGetHotel()
    }

    private fun initObservers() {
        viewModel.hotel.observe(viewLifecycleOwner) { it ->
            it.fold(onSuccess = { hotelResponse ->
                with(binding) {
                    val viewPager = viewpagerPhotos
                    viewPager.adapter =
                        CustomPagerAdapter(requireContext(), hotelResponse.image_urls)

                    tlPhotos.setupWithViewPager(viewPager, true)

                    tvRating.text = hotelResponse.rating.toString()
                    tvRatingName.text = hotelResponse.rating_name
                    tvHotelName.text = hotelResponse.name
                    btnAddress.text = hotelResponse.adress
                    tvPrice.text = hotelResponse.minimal_price.toString().moneyType()
                    tvPriceForIt.text = hotelResponse.price_for_it
                    hotelResponse.about_the_hotel.peculiarities.forEach { description ->
                        binding.cgPeculiarities.addView(
                            createPeculiarityChip(
                                requireContext(),
                                description
                            )
                        )
                    }
                    tvDescription.text = hotelResponse.about_the_hotel.description
                    binding.btnToNumber.setOnClickListener {
                        navigate(
                            R.id.action_hotelFragment_to_numberFragment,
                            data = hotelResponse.name
                        )
                    }
                }
            }, onFailure = {
                Log.e("e", it.message.toString())
            })
        }

        viewModel.error.observe(viewLifecycleOwner) {
            when (it) {
                is Exception -> {
                    showMessage(R.string.error)
                }
            }
        }
    }

    private fun showMessage(stringId: Int) {
        Snackbar.make(
            binding.root,
            stringId,
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun createPeculiarityChip(context: Context, chipName: String): Chip {
        return Chip(context).apply {
            text = chipName
            setChipBackgroundColorResource(com.garif.core.R.color.whiteout)
            setTextColor(ContextCompat.getColor(context, com.garif.core.R.color.silver))
            chipStrokeWidth = context.resources.getDimension(com.garif.core.R.dimen.x0)
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
}