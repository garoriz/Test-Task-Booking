package com.garif.hotel_feature.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.garif.hotel_feature.domain.usecase.GetHotelUseCase
import com.garif.network.response.HotelResponse
import kotlinx.coroutines.launch
import javax.inject.Inject

class HotelViewModel @Inject constructor(
    private val getHotelUseCase: GetHotelUseCase,
) : ViewModel() {
    private var _hotel: MutableLiveData<Result<HotelResponse>> = MutableLiveData()
    val hotel: LiveData<Result<HotelResponse>> = _hotel

    private var _error: MutableLiveData<Exception> = MutableLiveData()
    val error: LiveData<Exception> = _error

    fun onGetHotel() {
        viewModelScope.launch {
            try {
                val hotel = getHotelUseCase()
                _hotel.value = Result.success(hotel)
            } catch (ex: Exception) {
                _hotel.value = Result.failure(ex)
                _error.value = ex
            }
        }
    }
}