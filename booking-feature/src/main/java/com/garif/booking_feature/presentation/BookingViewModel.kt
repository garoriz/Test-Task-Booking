package com.garif.booking_feature.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.garif.booking_feature.domain.entity.BookingInfo
import com.garif.booking_feature.domain.usecase.GetBookingInfoUseCase
import com.garif.network.response.hotel.HotelResponse
import kotlinx.coroutines.launch
import javax.inject.Inject

class BookingViewModel @Inject constructor(
    private val getBookingInfoUseCase: GetBookingInfoUseCase,
) : ViewModel() {
    private var _bookingInfo: MutableLiveData<Result<BookingInfo>> = MutableLiveData()
    val bookingInfo: LiveData<Result<BookingInfo>> = _bookingInfo

    private var _error: MutableLiveData<Exception> = MutableLiveData()
    val error: LiveData<Exception> = _error

    fun onGetBookingInfo() {
        viewModelScope.launch {
            try {
                val bookingInfo = getBookingInfoUseCase()
                _bookingInfo.value = Result.success(bookingInfo)
            } catch (ex: Exception) {
                _bookingInfo.value = Result.failure(ex)
                _error.value = ex
            }
        }
    }
}