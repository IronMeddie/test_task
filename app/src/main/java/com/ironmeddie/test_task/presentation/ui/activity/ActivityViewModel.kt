package com.ironmeddie.test_task.presentation.ui.activity

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ironmeddie.test_task.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

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
            try {
                val response = repository.getCart()
                if (response.isSuccessful) {
                    response.body().let { res ->

                        _cart.value = res?.basket?.size ?: 0
                        Log.d("chekCode", res?.basket?.size.toString())
                    }
                } else Log.d("chekCode", response.message())
            } catch (e: IOException) {
                Log.d("chekCode", e.message.toString())
            }

        }
    }

}