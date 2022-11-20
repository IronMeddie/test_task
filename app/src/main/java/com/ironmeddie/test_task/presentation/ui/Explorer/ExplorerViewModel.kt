package com.ironmeddie.test_task.presentation.ui.Explorer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ironmeddie.data.DataResource
import com.ironmeddie.data.Repository
import com.ironmeddie.domain.models.ResponseMainScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExplorerViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private val _mainScreeData :MutableStateFlow<DataResource<ResponseMainScreen>> = MutableStateFlow(DataResource.Loading)
    val mainScreeData : StateFlow<DataResource<ResponseMainScreen>> = _mainScreeData

    init {
        getInfo()
    }

    fun getInfo() =
        viewModelScope.launch {
            _mainScreeData.value = repository.getMainScreenSafe()
        }



}