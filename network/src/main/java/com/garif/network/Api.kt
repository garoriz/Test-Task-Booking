package com.garif.network

import com.garif.network.response.hotel.HotelResponse
import com.garif.network.response.numbers.NumbersResponse
import retrofit2.http.GET

interface Api {
    @GET("d144777c-a67f-4e35-867a-cacc3b827473")
    suspend fun getHotel(): HotelResponse

    @GET("8b532701-709e-4194-a41c-1a903af00195")
    suspend fun getNumbers(): NumbersResponse
}