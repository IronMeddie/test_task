package com.ironmeddie.test_task.presentation.ui.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ironmeddie.test_task.data.Repository
import com.ironmeddie.test_task.domain.models.Details
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val details2 = Details(CPU = "Loading..", camera = "Loading..", capacity = listOf("Loading..", "Loading.."), color = listOf("#772D03" ,"#010035" ), id = "3", images = listOf("https://avatars.mds.yandex.net/get-mpic/5235334/img_id5575010630545284324.png/orig", "https://www.manualspdf.ru/thumbs/products/l/1260237-samsung-galaxy-note-20-ultra.jpg"), isFavorites = false, price = 0, rating = 0.0, sd = "Loading..", ssd = "Loading..", title = "Loading.." )

    private val _details : MutableStateFlow<Details> = MutableStateFlow(details2)
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