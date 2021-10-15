package com.vinyla_android.presentation.vinyl.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinyla_android.domain.entity.Vinyl
import com.vinyla_android.domain.repository.VinylsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created By Malibin
 * on 10월 12, 2021
 */

@HiltViewModel
class VinylDetailsViewModel @Inject constructor(
    private val vinylsRepository: VinylsRepository,
) : ViewModel() {

    private val _vinyl = MutableLiveData<Vinyl>()
    val vinyl: LiveData<Vinyl> = _vinyl

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    fun getVinylDetails(vinylId: Int) = viewModelScope.launch {
        _isLoading.value = true
        _vinyl.value = vinylsRepository.getVinylOf(vinylId)
    }.invokeOnCompletion {
        _isLoading.value = false
    }
}
