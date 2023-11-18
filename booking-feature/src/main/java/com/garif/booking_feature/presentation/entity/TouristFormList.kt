package com.garif.booking_feature.presentation.entity

data class TouristFormList(
    val touristFormList: MutableList<String> = mutableListOf(
        "Первый турист",
        "Второй турист",
        "Третий турист",
        "Четвёртый турист",
        "Пятый турист",
    )
)
