package com.vinyla_android.presentation.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    fun notifyLoading(isLoading: Boolean) {
        _isLoading.value = isLoading
    }
}
