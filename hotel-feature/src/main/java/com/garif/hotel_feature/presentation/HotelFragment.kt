package com.garif.hotel_feature.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.garif.core.util.AppViewModelFactory
import com.garif.hotel_feature.DaggerHotelFeatureComponent
import com.garif.hotel_feature.HotelFeatureComponentDependenciesProvider
import com.garif.hotel_feature.R
import com.garif.hotel_feature.TaskRepository
import com.garif.hotel_feature.databinding.FragmentHotelBinding
import com.garif.hotel_feature.presentation.adapter.CustomPagerAdapter
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject


class HotelFragment : Fragment(R.layout.fragment_hotel) {
    @Inject
    lateinit var factory: AppViewModelFactory
    private lateinit var binding: FragmentHotelBinding
    private val viewModel: HotelViewModel by viewModels {
        factory
    }
    @Inject
    lateinit var taskRepository: TaskRepository


    override fun onAttach(context: Context) {
        super.onAttach(context)
        val hotelFeatureComponentDependencies = (context.applicationContext as HotelFeatureComponentDependenciesProvider)
            .getHotelFeatureComponentDependencies()
        val taskComponent = DaggerHotelFeatureComponent.builder()
            .hotelFeatureComponentDependencies(hotelFeatureComponentDependencies)
            .build()
        taskComponent.injectHotelFragment(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHotelBinding.bind(view)

        initObservers()
        viewModel.onGetHotel()
    }

    private fun initObservers() {
        viewModel.hotel.observe(viewLifecycleOwner) { it ->
            it.fold(onSuccess = {
                with(binding) {
                    val viewPager = viewpagerPhotos
                    viewPager.adapter = CustomPagerAdapter(requireContext(), it.image_urls)
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