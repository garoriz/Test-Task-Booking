package com.garif.testtaskbooking

import android.app.Application
import com.garif.hotel_feature.HotelFeatureComponent
import com.garif.hotel_feature.HotelFeatureComponentDependencies
import com.garif.hotel_feature.HotelFeatureComponentDependenciesProvider
import com.garif.hotel_feature.HotelFeatureComponentProvider

class App : Application(), HotelFeatureComponentDependenciesProvider {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }

    override fun getHotelFeatureComponentDependencies(): HotelFeatureComponentDependencies {
        return appComponent
    }

}