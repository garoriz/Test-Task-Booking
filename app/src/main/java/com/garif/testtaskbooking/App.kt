package com.garif.testtaskbooking

import android.app.Application
import com.garif.hotel_feature.di.HotelFeatureComponent
import com.garif.hotel_feature.di.HotelFeatureComponentProvider
import com.garif.number_feature.di.NumbersFeatureComponent
import com.garif.number_feature.di.NumbersFeatureComponentProvider

class App : Application(), HotelFeatureComponentProvider, NumbersFeatureComponentProvider {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }

    override fun getHotelFeatureComponent(): HotelFeatureComponent {
        return appComponent.createHotelComponent()
    }

    override fun getNumbersFeatureComponent(): NumbersFeatureComponent {
        return appComponent.createNumbersComponent()
    }

}