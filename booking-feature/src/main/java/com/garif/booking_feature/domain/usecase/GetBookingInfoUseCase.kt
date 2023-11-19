package com.garif.booking_feature.domain.usecase

import com.garif.booking_feature.domain.entity.BookingInfo
import com.garif.booking_feature.domain.repo.BookingInfoRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetBookingInfoUseCase @Inject constructor(
    private val bookingInfoRepo: BookingInfoRepo,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend operator fun invoke(): BookingInfo {
        return withContext(dispatcher) {
            bookingInfoRepo.getBookingInfo()
        }
    }
}