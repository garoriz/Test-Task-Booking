package com.garif.number_feature.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.garif.core.navigate
import com.garif.core.navigationData
import com.garif.core.util.AppViewModelFactory
import com.garif.network.response.numbers.Room
import com.garif.number_feature.R
import com.garif.number_feature.databinding.FragmentNumberBinding
import com.garif.number_feature.di.NumbersFeatureComponentProvider
import com.garif.number_feature.presentation.adapter.NumberListAdapter
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class NumbersFragment : Fragment(R.layout.fragment_number) {
    @Inject
    lateinit var factory: AppViewModelFactory
    private lateinit var binding: FragmentNumberBinding
    private val viewModel: NumbersViewModel by viewModels {
        factory
    }
    private var numberListAdapter: NumberListAdapter? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as NumbersFeatureComponentProvider)
            .getNumbersFeatureComponent()
            .injectNumbersFragment(this)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentNumberBinding.bind(view)

        with(binding) {
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
            tvHotelName.text = navigationData as? String ?: return
        }

        initObservers()
        viewModel.onGetNumbers()
    }

    private fun initObservers() {
        viewModel.numbers.observe(viewLifecycleOwner) { it ->
            it.fold(onSuccess = { numbersResponse ->
                with(binding) {
                    numberListAdapter = NumberListAdapter(requireContext()) {

                    }

                    binding.numbers.run {
                        adapter = numberListAdapter
                    }

                    numberListAdapter?.submitList(numbersResponse.rooms as MutableList<Room>)
                }
            }, onFailure = {
                Log.e("e", it.message.toString())
            })
        }

        viewModel.error.observe(viewLifecycleOwner) {
            when (it) {
                is Exception -> {
                    showMessage(com.garif.core.R.string.error)
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