package com.garif.number_feature.domain.repo

import com.garif.network.response.numbers.NumbersResponse

interface NumbersRepo {
    suspend fun getNumbers(): NumbersResponse
}