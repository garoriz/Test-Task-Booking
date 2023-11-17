package com.garif.hotel_feature.di.module

import com.garif.hotel_feature.data.HotelRepoImpl
import com.garif.hotel_feature.domain.repo.HotelRepo
import dagger.Binds
import dagger.Module

@Module
interface RepoModule {
    @Binds
    fun hotelRepo(
        impl: HotelRepoImpl
    ): HotelRepo
}