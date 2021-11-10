package com.vinyla_android.presentation.utils

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinyla_android.domain.event.SingleLiveEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {
    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _toastEvent = SingleLiveEvent<ToastMessage>()
    val toastEvent: LiveData<ToastMessage> = _toastEvent

    fun launchViewModelScopeWithLoading(block: suspend CoroutineScope.() -> Unit): Job {
        notifyLoading(true)
        return viewModelScope.launch(block = block)
            .also { it.invokeOnCompletion { notifyLoading(false) } }
    }

    fun notifyLoading(isLoading: Boolean) {
        _isLoading.value = isLoading
    }

    fun notifyToastMessage(message: String?) {
        _toastEvent.value = ToastMessage.StringValue(message.orEmpty())
    }

    fun notifyToastMessage(@StringRes resourceId: Int) {
        _toastEvent.value = ToastMessage.ResourceId(resourceId)
    }

    fun notifyToastMessage(toastMessage: ToastMessage) {
        _toastEvent.value = toastMessage
    }
}
