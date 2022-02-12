package com.vinyla_android.presentation.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.malibin.sns.auth.SnsAuth
import com.malibin.sns.auth.model.SnsType
import com.vinyla_android.domain.event.SingleLiveEvent
import com.vinyla_android.domain.repository.VinylaMembersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeaveViewModel @Inject constructor(
    private val vinylaMembersRepository: VinylaMembersRepository,
    private val snsAuth: SnsAuth,
) : ViewModel() {
    private val _isLeaveSuccess = SingleLiveEvent<Unit>()
    val isLeaveSuccess: LiveData<Unit> = _isLeaveSuccess

    val isCautionChecked = MutableLiveData<Boolean>()

    fun leave() = viewModelScope.launch {
        vinylaMembersRepository.deleteVinylaToken()
        // TODO 탈퇴하는 바닐라 API 쏴야함
        val snsType = when (vinylaMembersRepository.getLoginSnsType()) {
            com.vinyla_android.domain.entity.member.SnsType.GOOGLE -> SnsType.GOOGLE
            com.vinyla_android.domain.entity.member.SnsType.APPLE -> SnsType.APPLE
            com.vinyla_android.domain.entity.member.SnsType.FACEBOOK -> SnsType.FACEBOOK
            else -> return@launch
        }
        snsAuth.unlink(snsType)
        _isLeaveSuccess.value = Unit
    }
}
