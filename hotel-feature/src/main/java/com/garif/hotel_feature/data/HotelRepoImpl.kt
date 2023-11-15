package com.garif.hotel_feature.data

import com.garif.hotel_feature.domain.repo.HotelRepo
import com.garif.network.Api
import com.garif.network.response.HotelResponse
import javax.inject.Inject

class HotelRepoImpl @Inject constructor(
    private val api: Api,
) : HotelRepo {
    override suspend fun getHotel(): HotelResponse {
        return api.getHotel()
    }
}