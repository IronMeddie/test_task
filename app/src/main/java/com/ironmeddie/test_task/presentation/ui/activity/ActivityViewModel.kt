package com.ironmeddie.test_task.presentation.ui.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ironmeddie.data.DataResource
import com.ironmeddie.domain.usecases.getDataFromApiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(private val getDataFromApiUseCase: getDataFromApiUseCase) : ViewModel() {

    private val _splashState = MutableStateFlow(false)
    val splashState: StateFlow<Boolean> = _splashState

    private val _cart = MutableStateFlow(0)
    val cart: StateFlow<Int> = _cart

    fun splash() {
        viewModelScope.launch {
            delay(2000)
            _splashState.value = true
        }
    }

    init {
        getCartCount()
    }


    fun getCartCount() {
        viewModelScope.launch {
            val data = getDataFromApiUseCase.getCartData()
            if (data is DataResource.Success){
                _cart.value =  data.value.basket.size
            }

        }


    }

}