package com.vinyla_android.presentation.vinyl.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vinyla_android.domain.entity.Vinyl
import com.vinyla_android.domain.repository.VinylsRepository
import com.vinyla_android.presentation.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created By Malibin
 * on 10월 12, 2021
 */

@HiltViewModel
class VinylDetailsViewModel @Inject constructor(
    private val vinylsRepository: VinylsRepository,
) : BaseViewModel() {

    private val _vinyl = MutableLiveData<Vinyl>()
    val vinyl: LiveData<Vinyl> = _vinyl

    private val _isCollected = MutableLiveData(false)
    val isCollected: LiveData<Boolean> = _isCollected

    fun loadVinylDetails(vinylId: Int) = launchViewModelScopeWithLoading {
        //        _vinyl.value = vinylsRepository.getVinylOf(vinylId)
    }

    fun makeRepresentativeVinyl() = launchViewModelScopeWithLoading {
        // 대표 바이닐 등록
    }

    fun removeCollectedVinyl() = launchViewModelScopeWithLoading {
        // 수집된 바이닐 삭제
    }
}
