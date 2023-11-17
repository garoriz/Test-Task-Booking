package com.garif.booking_feature.domain.entity

data class BookingInfo (
    val hotelName: String,
    val hotelAddress: String,
    val hotelRating: Int,
    val ratingName: String,
    val departure: String,
    val arrivalCountry: String,
    val tourDates: String,
    val numberOfNights: Int,
    val room: String,
    val nutrition: String,
    val tourPrice: String,
    val fuelCharge: String,
    val serviceCharge: String,
    val finalPrice: String,
    val payButtonText: String
)