package com.garif.booking_feature.di.module

import com.garif.booking_feature.data.mappers.BookingInfoMapper
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
class BookingModule {
    @Provides
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @Provides
    fun provideBookingInfoMapper(): BookingInfoMapper = BookingInfoMapper()

}