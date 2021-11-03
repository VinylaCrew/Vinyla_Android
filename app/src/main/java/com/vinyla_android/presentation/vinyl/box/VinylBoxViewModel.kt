package com.vinyla_android.presentation.vinyl.box

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinyla_android.domain.entity.Vinyl
import com.vinyla_android.domain.entity.Vinyls
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created By Malibin
 * on 8월 02, 2021
 */

@HiltViewModel
class VinylBoxViewModel @Inject constructor() : ViewModel() {

    private val _pagedCollectedVinyls = MutableLiveData<List<Vinyls>>()
    val pagedCollectedVinyls: LiveData<List<Vinyls>> = _pagedCollectedVinyls

    fun loadCollectedVinyls() = viewModelScope.launch {
//        _pagedCollectedVinyls.value = STUB_VINYLS
    }
}
