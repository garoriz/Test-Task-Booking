package com.garif.number_feature.data

import com.garif.network.Api
import com.garif.network.response.numbers.NumbersResponse
import com.garif.number_feature.domain.repo.NumbersRepo
import javax.inject.Inject

class NumbersRepoImpl @Inject constructor(
    private val api: Api,
) : NumbersRepo {
    override suspend fun getNumbers(): NumbersResponse {
        return api.getNumbers()
    }

}