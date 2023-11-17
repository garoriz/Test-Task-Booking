package com.garif.booking_feature.data.mappers

import com.garif.booking_feature.domain.entity.BookingInfo
import com.garif.core.util.moneyType
import com.garif.network.response.bookinginfo.BookingInfoResponse

class BookingInfoMapper {
    fun map(response: BookingInfoResponse): BookingInfo {
        val finalPrice =
            (response.tour_price + response.fuel_charge + response.service_charge).toString()
                .moneyType() + " ₽"
        return BookingInfo(
            hotelName = response.hotel_name,
            hotelAddress = response.hotel_adress,
            hotelRating = response.horating,
            ratingName = response.rating_name,
            departure = response.departure,
            arrivalCountry = response.arrival_country,
            tourDates = response.tour_date_start + " - " + response.tour_date_stop,
            numberOfNights = response.number_of_nights,
            room = response.room,
            nutrition = response.nutrition,
            fuelCharge = response.fuel_charge.toString().moneyType() + " ₽",
            serviceCharge = response.service_charge.toString().moneyType() + " ₽",
            tourPrice = response.tour_price.toString().moneyType() + " ₽",
            finalPrice = finalPrice,
            payButtonText = "Оплатить $finalPrice"
        )
    }
}