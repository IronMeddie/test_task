package com.ironmeddie.test_task.presentation.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ironmeddie.data.DataResource
import com.ironmeddie.domain.models.Cart
import com.ironmeddie.domain.usecases.getDataFromApiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CartViewModel @Inject constructor(private val getDataFromApiUseCase : getDataFromApiUseCase) : ViewModel() {

    private val _cart : MutableStateFlow<DataResource<Cart>> = MutableStateFlow(DataResource.Loading)
    val cart : StateFlow<DataResource<Cart>> = _cart


    init {
        getInfo()
    }


    fun getInfo() =
        viewModelScope.launch {

            _cart.value = getDataFromApiUseCase.getCartData()

        }

}