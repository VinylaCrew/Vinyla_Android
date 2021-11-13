package com.vinyla_android.presentation.vinyl.box

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinyla_android.domain.entity.Vinyls
import com.vinyla_android.domain.repository.VinylsRepository
import com.vinyla_android.presentation.utils.printLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created By Malibin
 * on 8월 02, 2021
 */

@HiltViewModel
class VinylBoxViewModel @Inject constructor(
    private val vinylsRepository: VinylsRepository,
) : ViewModel() {

    private val _pagedCollectedVinyls = MutableLiveData<List<Vinyls>>()
    val pagedCollectedVinyls: LiveData<List<Vinyls>> = _pagedCollectedVinyls

    fun loadCollectedVinyls() = viewModelScope.launch {
        _pagedCollectedVinyls.value = vinylsRepository.getCollectedVinyls()
            .get()
            .chunked(9) { Vinyls.from(it) }
            .also { printLog(it.toString()) }
    }
}
