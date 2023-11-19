package com.garif.testtaskbooking

import android.content.Context
import com.garif.booking_feature.di.BookingFeatureComponent
import com.garif.hotel_feature.di.HotelFeatureComponent
import com.garif.number_feature.di.NumbersFeatureComponent
import com.garif.paid_feature.di.PaidFeatureComponent
import dagger.BindsInstance
import dagger.Component

@Component()
interface AppComponent {
    fun inject(mainActivity: MainActivity)

    fun createHotelComponent(): HotelFeatureComponent

    fun createNumbersComponent(): NumbersFeatureComponent

    fun createBookingComponent(): BookingFeatureComponent

    fun createPaidComponent(): PaidFeatureComponent

    @Component.Factory
    interface AppComponentFactory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}