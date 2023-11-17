package com.garif.booking_feature.di

import com.garif.booking_feature.di.module.BookingModule
import com.garif.booking_feature.di.module.RepoModule
import com.garif.booking_feature.di.module.ViewModelModule
import com.garif.booking_feature.presentation.BookingFragment
import com.garif.network.NetworkModule
import dagger.Subcomponent

@Subcomponent(
    modules = [
        NetworkModule::class,
        ViewModelModule::class,
        BookingModule::class,
        RepoModule::class]
)
interface BookingFeatureComponent {
    fun injectBookingFragment(bookingFragment: BookingFragment)
}