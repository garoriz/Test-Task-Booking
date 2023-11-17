package com.garif.booking_feature.di.module

import com.garif.booking_feature.data.BookingInfoRepoImpl
import com.garif.booking_feature.domain.repo.BookingInfoRepo
import dagger.Binds
import dagger.Module

@Module
interface RepoModule {
    @Binds
    fun bookingInfoRepo(
        impl: BookingInfoRepoImpl
    ): BookingInfoRepo
}