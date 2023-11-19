package com.garif.number_feature.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.garif.network.response.numbers.NumbersResponse
import com.garif.number_feature.domain.usecase.GetNumbersUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class NumbersViewModel @Inject constructor(
    private val getNumbersUseCase: GetNumbersUseCase,
) : ViewModel() {
    private var _numbers: MutableLiveData<Result<NumbersResponse>> = MutableLiveData()
    val numbers: LiveData<Result<NumbersResponse>> = _numbers

    private var _error: MutableLiveData<Exception> = MutableLiveData()
    val error: LiveData<Exception> = _error

    fun onGetNumbers() {
        viewModelScope.launch {
            try {
                val numbers = getNumbersUseCase()
                _numbers.value = Result.success(numbers)
            } catch (ex: Exception) {
                _numbers.value = Result.failure(ex)
                _error.value = ex
            }
        }
    }
}