package com.garif.booking_feature.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.garif.booking_feature.R
import com.garif.booking_feature.databinding.FragmentBookingBinding
import com.garif.booking_feature.di.BookingFeatureComponentProvider
import com.garif.booking_feature.presentation.adapter.TouristFormListAdapter
import com.garif.booking_feature.presentation.entity.TouristFormList
import com.garif.core.util.AppViewModelFactory
import com.garif.core.util.isEmailValid
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import javax.inject.Inject


class BookingFragment : Fragment(R.layout.fragment_booking) {
    @Inject
    lateinit var factory: AppViewModelFactory
    private lateinit var binding: FragmentBookingBinding
    private val viewModel: BookingViewModel by viewModels {
        factory
    }
    private var touristFormListAdapter: TouristFormListAdapter? = null
    private var visibleTouristsCount = 1
    private val textInputLayoutList = mutableListOf<TextInputLayout>()
    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as BookingFeatureComponentProvider)
            .getBookingFeatureComponent()
            .injectBookingFragment(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentBookingBinding.bind(view)


        with(binding) {
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }

            touristFormListAdapter = TouristFormListAdapter(textInputLayoutList)

            binding.touristForms.run {
                adapter = touristFormListAdapter
            }

            touristFormListAdapter?.submitList(
                TouristFormList().touristFormList.subList(
                    0,
                    visibleTouristsCount
                )
            )

            btnAdd.setOnClickListener {
                visibleTouristsCount++
                touristFormListAdapter?.submitList(
                    TouristFormList().touristFormList.subList(
                        0,
                        visibleTouristsCount
                    )
                )
            }

            btnPay.setOnClickListener {

            }
        }

        listenTilEmail()
        initObservers()
        viewModel.onGetBookingInfo()
    }

    private fun listenTilEmail() {
        with(binding) {
            tilEmail.editText?.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                if (!hasFocus and !tilEmail.editText?.text.toString().isEmailValid()) {
                    tilEmail.boxBackgroundColor =
                        ContextCompat.getColor(requireContext(), com.garif.core.R.color.red)
                } else if (!hasFocus and tilEmail.editText?.text.toString().isEmailValid()) {
                    tilEmail.boxBackgroundColor =
                        ContextCompat.getColor(requireContext(), com.garif.core.R.color.grams_hair)
                }
            }
        }
    }

    private fun initObservers() {
        viewModel.bookingInfo.observe(viewLifecycleOwner) { it ->
            it.fold(onSuccess = {
                with(binding) {
                    tvRating.text = it.hotelRating.toString()
                    tvRatingName.text = it.ratingName
                    tvHotelName.text = it.hotelName
                    btnAddress.text = it.hotelAddress
                    tvArrivingFromValue.text = it.departure
                    tvCountryCityValue.text = it.arrivalCountry
                    tvDatesValue.text = it.tourDates

                    val countOfNightsText =
                        it.numberOfNights.toString() + " " + resources.getQuantityString(
                            R.plurals.nights,
                            it.numberOfNights
                        )
                    tvNightCountsValue.text = countOfNightsText
                    tvHotelValue.text = it.hotelName
                    tvNumberValue.text = it.room
                    tvNourishmentValue.text = it.nutrition
                    tvTourValue.text = it.tourPrice
                    tvTourValue.text = it.tourPrice
                    tvFuelSurchargeValue.text = it.fuelCharge
                    tvServiceSurchargeValue.text = it.serviceCharge
                    tvPayableValue.text = it.finalPrice
                    btnPay.text = it.payButtonText
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
}