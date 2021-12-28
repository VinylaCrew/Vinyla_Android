package com.vinyla_android.presentation.vinyl.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vinyla_android.domain.entity.vinyl.Vinyl
import com.vinyla_android.domain.event.SingleLiveEvent
import com.vinyla_android.domain.event.SubmitEvent
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

    private val _removeCollectEvent = SingleLiveEvent<SubmitEvent>()
    val removeCollectEvent: LiveData<SubmitEvent> = _removeCollectEvent

    private val _vinyl = MutableLiveData<Vinyl>()
    val vinyl: LiveData<Vinyl> = _vinyl

    private val _isCollected = MutableLiveData(false)
    val isCollected: LiveData<Boolean> = _isCollected

    fun loadVinylDetails(vinylId: Int) = launchViewModelScopeWithLoading {
        val vinyl = vinylsRepository.getVinylOf(vinylId)
        if (vinyl != null) {
            _vinyl.value = vinyl
            _isCollected.value = vinyl.isCollected
            return@launchViewModelScopeWithLoading
        }
        notifyToastMessage("해당 바이닐을 찾을 수 없습니다!")
        // TODO: getVinyl의 반환을 Result<Vinyl>로 바꿀 것.
    }

    fun makeRepresentativeVinyl() = launchViewModelScopeWithLoading {
        // 대표 바이닐 등록
    }

    fun removeCollectedVinyl() = launchViewModelScopeWithLoading {
        vinylsRepository.cancelCollectVinyl(getVinylId())
            .onSuccess { _removeCollectEvent.value = SubmitEvent.Success }
            .onFailure { _removeCollectEvent.value = SubmitEvent.Fail(it) }
    }

    private fun getVinylId(): Int = _vinyl.value?.id ?: error("vinyl is not loaded yet")
}
