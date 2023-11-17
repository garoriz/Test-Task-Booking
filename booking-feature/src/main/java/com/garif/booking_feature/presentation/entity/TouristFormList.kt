package com.garif.booking_feature.presentation.entity

data class TouristFormList(
    val touristFormList: MutableList<TouristForm> = mutableListOf(
        TouristForm("Первый турист"),
        TouristForm("Второй турист"),
        TouristForm("Третий турист"),
        TouristForm("Четвёртый турист"),
        TouristForm("Пятый турист"),
    )
)
