package com.garif.hotel_feature.domain.repo

import com.garif.network.response.HotelResponse

interface HotelRepo {
    suspend fun getHotel(): HotelResponse
}