package com.vinyla_android.presentation.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {
    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    fun launchViewModelScopeWithLoading(block: suspend CoroutineScope.() -> Unit): Job {
        notifyLoading(true)
        return viewModelScope.launch(block = block)
            .also { it.invokeOnCompletion { notifyLoading(false) } }
    }

    fun notifyLoading(isLoading: Boolean) {
        _isLoading.value = isLoading
    }
}
