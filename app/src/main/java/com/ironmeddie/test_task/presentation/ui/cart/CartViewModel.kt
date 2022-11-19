package com.ironmeddie.test_task.presentation.ui.cart

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ironmeddie.data.Repository
import com.ironmeddie.domain.models.Cart
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class CartViewModel @Inject constructor(private val repository : com.ironmeddie.data.Repository) : ViewModel() {

    private val _cart : MutableStateFlow<com.ironmeddie.domain.models.Cart> = MutableStateFlow(
        com.ironmeddie.domain.models.Cart(
            listOf(),
            "",
            "",
            0
        )
    )
    val cart : StateFlow<com.ironmeddie.domain.models.Cart> = _cart


    init {
        getInfo()
    }


    fun getInfo() =
        viewModelScope.launch {
            try {
                val response = repository.getCart()
                if (response.isSuccessful) {
                    response.body().let { res ->
                        _cart.value = res!!
                    }
                } else Log.d("chekCode", response.message())
            }catch (e: IOException){
                Log.d("chekCode", e.message.toString())
            }

        }

}