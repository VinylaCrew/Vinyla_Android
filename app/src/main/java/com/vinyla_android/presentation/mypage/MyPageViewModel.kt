package com.vinyla_android.presentation.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinyla_android.domain.entity.member.SnsType
import com.vinyla_android.domain.repository.VinylaMembersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class MyPageViewModel(
    private val vinylaMembersRepository: VinylaMembersRepository,
) : ViewModel() {

    private val _nickname = MutableLiveData<String>()
    val nickname: LiveData<String> = _nickname

    private val _loginSnsType = MutableLiveData<SnsType>()
    val loginSnsType: LiveData<SnsType> = _loginSnsType

    private var initialIsMarketingAgreed: Boolean = false
    val isMarketingAgreed = MutableLiveData<Boolean>(false)


    init {
        viewModelScope.launch {
            _nickname.value = vinylaMembersRepository.getNickname()
            _loginSnsType.value = vinylaMembersRepository.getLoginSnsType()
            vinylaMembersRepository.getMarketingAgreed().also {
                initialIsMarketingAgreed = it
                isMarketingAgreed.value = it
            }
        }
    }

    override fun onCleared() {
        val currentIsMarketingAgreed = isMarketingAgreed.value ?: false
        val isMarketingAgreedChanged = initialIsMarketingAgreed != currentIsMarketingAgreed
        if (isMarketingAgreedChanged) {
            saveMarketingAgree(currentIsMarketingAgreed)
        }
    }

    private fun saveMarketingAgree(isAgreed: Boolean) = CoroutineScope(Dispatchers.IO).launch {
        vinylaMembersRepository.saveMarketingAgreed(isAgreed)
    }
}
