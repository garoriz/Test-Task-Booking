package com.garif.hotel_feature.di

import com.garif.hotel_feature.TaskModule
import com.garif.hotel_feature.di.module.HotelModule
import com.garif.hotel_feature.di.module.RepoModule
import com.garif.hotel_feature.di.module.ViewModelModule
import com.garif.hotel_feature.presentation.HotelFragment
import com.garif.network.NetworkModule
import dagger.Subcomponent

@Subcomponent(
    modules = [
        NetworkModule::class,
        TaskModule::class,
        ViewModelModule::class,
        HotelModule::class,
        RepoModule::class]
)
interface HotelFeatureComponent {
    fun injectHotelFragment(hotelFragment: HotelFragment)
}