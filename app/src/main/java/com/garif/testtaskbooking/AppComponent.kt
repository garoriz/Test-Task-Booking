package com.garif.testtaskbooking

import android.content.Context
import com.garif.core.CoreModule
import com.garif.hotel_feature.HotelFeatureComponentDependencies
import com.garif.network.NetworkModule
import dagger.BindsInstance
import dagger.Component

@Component(modules = [CoreModule::class])
interface AppComponent : HotelFeatureComponentDependencies {
    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface AppComponentFactory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}