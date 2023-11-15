package com.garif.hotel_feature.di

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
class HotelModule {
    @Provides
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
}