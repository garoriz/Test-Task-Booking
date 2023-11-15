package com.garif.hotel_feature.domain.usecase

import com.garif.hotel_feature.domain.repo.HotelRepo
import com.garif.network.response.HotelResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetHotelUseCase @Inject constructor(
    private val hotelRepo: HotelRepo,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend operator fun invoke(): HotelResponse {
        return withContext(dispatcher) {
            hotelRepo.getHotel()
        }
    }
}