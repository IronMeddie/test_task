package com.ironmeddie.test_task.presentation.ui.Explorer

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ironmeddie.test_task.data.Repository
import com.ironmeddie.test_task.domain.models.BestSeller
import com.ironmeddie.test_task.domain.models.HomeStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ExplorerViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private val _hotSales :MutableStateFlow<List<HomeStore>> = MutableStateFlow(listOf())
    val hotSales : StateFlow<List<HomeStore>> = _hotSales

    private val _bestSellers : MutableStateFlow<List<BestSeller>> = MutableStateFlow(listOf())
    val bestSellers : StateFlow<List<BestSeller>> = _bestSellers

    init {
        getInfo()
    }


    fun getInfo() =
        viewModelScope.launch {
            try {
                val response = repository.getMainInfo()
                if (response.isSuccessful) {
                    response.body().let { res ->
                        _hotSales.value = res!!.home_store
                        _bestSellers.value = res.best_seller
                    }
                } else Log.d("chekCode", response.message())
            }catch (e: IOException){
                Log.d("chekCode", e.message.toString())
            }

        }

}