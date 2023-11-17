package com.garif.booking_feature.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.garif.booking_feature.presentation.BookingViewModel
import com.garif.core.di.ViewModelKey
import com.garif.core.util.AppViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    fun bindViewModelFactory(
        factory: AppViewModelFactory
    ): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(BookingViewModel::class)
    fun bindBookingViewModel(
        viewModel: BookingViewModel
    ): ViewModel
}