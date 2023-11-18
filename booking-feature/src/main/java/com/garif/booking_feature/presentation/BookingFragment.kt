package com.garif.booking_feature.presentation

import android.content.Context
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.garif.booking_feature.R
import com.garif.booking_feature.databinding.FragmentBookingBinding
import com.garif.booking_feature.di.BookingFeatureComponentProvider
import com.garif.booking_feature.presentation.adapter.TouristFormListAdapter
import com.garif.booking_feature.presentation.entity.TouristFormList
import com.garif.core.navigate
import com.garif.core.util.AppViewModelFactory
import com.garif.core.util.isValidEmail
import com.garif.core.util.isValidPhoneNumber
import com.google.android.material.card.MaterialCardView
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
    private var visibleTouristFormIndex = 0
    private val textInputLayoutList = mutableListOf<TextInputLayout>()
    private val touristFormList = mutableListOf<ViewGroup>()
    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as BookingFeatureComponentProvider)
            .getBookingFeatureComponent()
            .injectBookingFragment(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentBookingBinding.bind(view)
        addTouristForm()
        visibleTouristFormIndex = 0


        with(binding) {
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }

            /*touristFormListAdapter = TouristFormListAdapter(textInputLayoutList)

            binding.touristForms.run {
                adapter = touristFormListAdapter
            }

            touristFormListAdapter?.submitList(
                TouristFormList().touristFormList.subList(
                    0,
                    visibleTouristsCount
                )
            )*/

            btnAdd.setOnClickListener {
                /*visibleTouristsCount++
                touristFormListAdapter?.submitList(
                    TouristFormList().touristFormList.subList(
                        0,
                        visibleTouristsCount
                    )
                )*/
                addTouristForm()
            }

            btnPay.setOnClickListener {
                if (isValidForm())
                    navigate(R.id.action_bookingFragment_to_paidFragment)
            }
        }

        listenTouristForms()
        listenTilEmail()
        initObservers()
        viewModel.onGetBookingInfo()
    }

    private fun isValidForm(): Boolean {
        var isValidForm = true
        for (touristForm in touristFormList) {
            isValidForm = isValidEditTextForCompleteness(
                touristForm.findViewById(R.id.til_name)
            ) and isValidEditTextForCompleteness(
                touristForm.findViewById(R.id.til_surname)
            ) and isValidEditTextForCompleteness(
                touristForm.findViewById(R.id.til_birthday)
            ) and isValidEditTextForCompleteness(
                touristForm.findViewById(R.id.til_citizenship)
            ) and isValidEditTextForCompleteness(
                touristForm.findViewById(R.id.til_passport)
            ) and isValidEditTextForCompleteness(
                touristForm.findViewById(R.id.til_passport_validity_period)
            ) and isValidPhoneNumber() and isValidEmail() and isValidForm
        }
        return isValidForm
    }

    private fun isValidEmail(): Boolean {
        with(binding) {
            return if (tilEmail.editText?.text.toString().isValidEmail()) {
                tilEmail.boxBackgroundColor =
                    ContextCompat.getColor(requireContext(), com.garif.core.R.color.grams_hair)
                true
            } else {
                tilEmail.boxBackgroundColor =
                    ContextCompat.getColor(requireContext(), com.garif.core.R.color.red)
                false

            }
        }
    }

    private fun isValidPhoneNumber(): Boolean {
        with(binding) {
            return if (tilPhoneNumber.editText?.text.toString().isValidPhoneNumber()) {
                tilPhoneNumber.boxBackgroundColor =
                    ContextCompat.getColor(requireContext(), com.garif.core.R.color.grams_hair)
                true
            } else {
                tilPhoneNumber.boxBackgroundColor =
                    ContextCompat.getColor(requireContext(), com.garif.core.R.color.red)
                false
            }
        }
    }

    private fun isValidEditTextForCompleteness(touristForm: TextInputLayout): Boolean {
        return if (touristForm.editText?.text.toString().isEmpty()) {
            touristForm.boxBackgroundColor =
                ContextCompat.getColor(requireContext(), com.garif.core.R.color.red)
            false
        } else {
            touristForm.boxBackgroundColor =
                ContextCompat.getColor(requireContext(), com.garif.core.R.color.grams_hair)
            true
        }
    }

    private fun addTouristForm() {
        with(binding) {
            val inflater = LayoutInflater.from(requireContext())
            val layout = inflater.inflate(
                R.layout.tourist_form_item,
                layoutTourists,
                false
            ) as ViewGroup
            layout.findViewById<TextView>(R.id.tv_tourist_number).text =
                TouristFormList().touristFormList[visibleTouristFormIndex]
            visibleTouristFormIndex++
            touristFormList.add(layout)

            (layout.layoutParams as ViewGroup.MarginLayoutParams).setMargins(0, resources.getDimension(
                com.garif.core.R.dimen.x1).toInt(), 0, 0)
            layoutTourists.addView(layout, layout.layoutParams)
            listenTouristForms()
        }
    }

    private fun listenTouristForms() {
        for (touristForm in touristFormList) {
            val hiddenView = touristForm.findViewById<ConstraintLayout>(R.id.hidden_view)
            val cvFirstTourist =
                touristForm.findViewById<MaterialCardView>(R.id.cv_first_tourist)
            val btnArrow = touristForm.findViewById<ImageButton>(R.id.btn_arrow)
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
        }
    }

    private fun listenTilEmail() {
        with(binding) {
            tilEmail.editText?.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                if (!hasFocus and !tilEmail.editText?.text.toString().isValidEmail()) {
                    tilEmail.boxBackgroundColor =
                        ContextCompat.getColor(requireContext(), com.garif.core.R.color.red)
                } else if (!hasFocus and tilEmail.editText?.text.toString().isValidEmail()) {
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