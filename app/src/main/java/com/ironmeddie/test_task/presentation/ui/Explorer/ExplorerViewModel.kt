package com.ironmeddie.test_task.presentation.ui.Explorer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ironmeddie.data.DataResource
import com.ironmeddie.domain.models.ResponseMainScreen
import com.ironmeddie.domain.usecases.getDataFromApiUseCase
import com.ironmeddie.test_task.presentation.ui.activity.Categories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExplorerViewModel @Inject constructor(private val getDataFromApiUseCase: getDataFromApiUseCase): ViewModel() {

    private val _mainScreeData :MutableStateFlow<DataResource<ResponseMainScreen>> = MutableStateFlow(DataResource.Loading)
    val mainScreeData : StateFlow<DataResource<ResponseMainScreen>> = _mainScreeData



    init {
        getInfo(Categories.CATEGORY_PHONES)
    }

    fun getInfo(categoryItem: String = Categories.CATEGORY_PHONES) =
        viewModelScope.launch {
            _mainScreeData.value = getDataFromApiUseCase.getMainScreenData()
        }



}