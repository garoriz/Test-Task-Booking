package com.garif.number_feature.domain.usecase

import com.garif.network.response.hotel.HotelResponse
import com.garif.network.response.numbers.NumbersResponse
import com.garif.number_feature.domain.repo.NumbersRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetNumbersUseCase @Inject constructor(
    private val numbersRepo: NumbersRepo,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend operator fun invoke(): NumbersResponse {
        return withContext(dispatcher) {
            numbersRepo.getNumbers()
        }
    }
}