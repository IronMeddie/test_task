package com.ironmeddie.test_task.presentation.ui.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ironmeddie.data.Repository
import com.ironmeddie.domain.models.Details
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val repository: Repository) : ViewModel() {


    private val _details : MutableStateFlow<Details> = MutableStateFlow(
        Details(title = "Loading..")
    )
    val details : StateFlow<Details> = _details

    init {
        getInfo()
    }


    fun getInfo() =
        viewModelScope.launch {
            try {
                val response = repository.getDetails()
                if (response.isSuccessful) {
                    response.body().let { res ->

                        _details.value = res!!
                    }
                } else Log.d("chekCode", response.message())
            }catch (e: IOException){
                Log.d("chekCode", e.message.toString())
            }

        }
}