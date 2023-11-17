package com.garif.hotel_feature.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.garif.core.di.ViewModelKey
import com.garif.core.util.AppViewModelFactory
import com.garif.hotel_feature.presentation.HotelViewModel
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
    @ViewModelKey(HotelViewModel::class)
    fun bindHotelViewModel(
        viewModel: HotelViewModel
    ): ViewModel
}