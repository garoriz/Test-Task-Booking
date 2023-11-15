package com.garif.hotel_feature

import com.garif.hotel_feature.di.HotelModule
import com.garif.hotel_feature.di.RepoModule
import com.garif.hotel_feature.di.ViewModelModule
import com.garif.hotel_feature.presentation.HotelFragment
import com.garif.network.NetworkModule
import dagger.Component

@Component(
    modules = [
        NetworkModule::class,
        TaskModule::class,
        ViewModelModule::class,
        HotelModule::class,
        RepoModule::class],
    dependencies = [
        HotelFeatureComponentDependencies::class
    ]
)
interface HotelFeatureComponent {
    fun injectHotelFragment(hotelFragment: HotelFragment)
}