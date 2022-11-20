package com.ironmeddie.test_task.presentation.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ironmeddie.data.DataResource
import com.ironmeddie.data.Repository
import com.ironmeddie.domain.models.Details
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val repository: Repository) : ViewModel() {


    private val _details : MutableStateFlow<DataResource<Details>> = MutableStateFlow(DataResource.Loading)
    val details : StateFlow<DataResource<Details>> = _details

    init {
        getInfo()
    }


    fun getInfo() =
        viewModelScope.launch {
            _details.value = repository.getDetails()
        }
}