package com.vinyla_android.presentation.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vinyla_android.R
import com.vinyla_android.domain.nickname.Nickname
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(

) : ViewModel() {
    private var availableNickname: String = ""
    val nickname = MutableLiveData("")
    val instagramId = MutableLiveData("")

    val isNicknameFormatted = MediatorLiveData<Boolean>().apply {
        addSource(nickname) { checkNicknameFormat(it) }
    }
    private val _isNicknameAvailable = MutableLiveData(false)
    val isNicknameAvailable: LiveData<Boolean> = _isNicknameAvailable

    private val _nicknameStateText = MutableLiveData(R.string.nickname_cannot_modify)
    val nicknameStateText: LiveData<Int> = _nicknameStateText

    val isTermsAndConditionOfServiceChecked = MutableLiveData(false)
    val isPrivacyPolicyChecked = MutableLiveData(false)
    val isMarketingChecked = MutableLiveData(false)

    val isAllChecked = MediatorLiveData<Boolean>().apply {
        addSource(isTermsAndConditionOfServiceChecked) { checkAllChecked() }
        addSource(isPrivacyPolicyChecked) { checkAllChecked() }
        addSource(isMarketingChecked) { checkAllChecked() }
    }

    private fun checkNicknameFormat(inputText: String) {
        isNicknameFormatted.value = Nickname.isValid(inputText)
    }

    private fun checkAllChecked() {
        val first = isTermsAndConditionOfServiceChecked.value ?: false
        val second = isPrivacyPolicyChecked.value ?: false
        val third = isMarketingChecked.value ?: false
        isAllChecked.value = first && second && third
    }

    fun clickAllCheck() {
        val currentChecked = isAllChecked.value ?: false
        isAllChecked.value = !currentChecked
        isTermsAndConditionOfServiceChecked.value = !currentChecked
        isPrivacyPolicyChecked.value = !currentChecked
        isMarketingChecked.value = !currentChecked
    }

    fun checkNicknameAvailable() {
        availableNickname =
            nickname.value ?: error("닉네임이 null일 때 checkNicknameAvailable()을 호출 할 수 없음.")
    }

    companion object {
        private const val MAX_NICKNAME_LENGTH = 25
    }
}
