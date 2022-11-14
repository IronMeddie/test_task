package com.ironmeddie.test_task.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ironmeddie.test_task.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CartViewModel @Inject constructor(private val repository : Repository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Cart Fragment"
    }
    val text: LiveData<String> = _text
}