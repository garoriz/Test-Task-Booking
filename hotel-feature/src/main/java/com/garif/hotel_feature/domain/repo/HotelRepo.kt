package com.garif.hotel_feature.domain.repo

import com.garif.network.response.hotel.HotelResponse

interface HotelRepo {
    suspend fun getHotel(): HotelResponse
}