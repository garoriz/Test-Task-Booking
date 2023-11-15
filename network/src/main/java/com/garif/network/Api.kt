package com.garif.network

import com.garif.network.response.HotelResponse
import retrofit2.http.GET

interface Api {
    @GET("d144777c-a67f-4e35-867a-cacc3b827473")
    suspend fun getHotel(): HotelResponse
}