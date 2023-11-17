package com.garif.booking_feature.domain.repo

import com.garif.booking_feature.domain.entity.BookingInfo

interface BookingInfoRepo {
    suspend fun getBookingInfo(): BookingInfo
}