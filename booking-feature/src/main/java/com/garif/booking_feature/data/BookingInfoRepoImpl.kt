package com.garif.booking_feature.data

import com.garif.booking_feature.data.mappers.BookingInfoMapper
import com.garif.booking_feature.domain.entity.BookingInfo
import com.garif.booking_feature.domain.repo.BookingInfoRepo
import com.garif.network.Api
import com.garif.network.response.hotel.HotelResponse
import javax.inject.Inject

class BookingInfoRepoImpl @Inject constructor(
    private val api: Api,
    private val bookingInfoMapper: BookingInfoMapper
) : BookingInfoRepo {
    override suspend fun getBookingInfo(): BookingInfo {
        return bookingInfoMapper.map(api.getBookingInfo())
    }

}