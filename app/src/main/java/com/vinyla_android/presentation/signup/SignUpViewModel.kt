package com.vinyla_android.presentation.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.vinyla_android.domain.entity.member.nickname.NicknameState
import com.vinyla_android.domain.usecase.member.CheckNicknameStateUseCase
import com.vinyla_android.presentation.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val checkNicknameStateUseCase: CheckNicknameStateUseCase,
) : BaseViewModel() {
    private var validNickname: String = ""
    val nickname = MutableLiveData("")
    val instagramId = MutableLiveData("")

    private val _nicknameState = MutableLiveData(NicknameState.UNCHECKED)
    val nicknameState: LiveData<NicknameState> = _nicknameState

    val isTermsAndConditionOfServiceChecked = MutableLiveData(false)
    val isPrivacyPolicyChecked = MutableLiveData(false)
    val isMarketingChecked = MutableLiveData(false)

    val isAllChecked = MediatorLiveData<Boolean>().apply {
        addSource(isTermsAndConditionOfServiceChecked) { checkAllChecked() }
        addSource(isPrivacyPolicyChecked) { checkAllChecked() }
        addSource(isMarketingChecked) { checkAllChecked() }
    }

    fun loadNickname(nickname: String) {
        this.nickname.value = nickname
    }

    fun checkNicknameFormat() = viewModelScope.launch {
        notifyLoading(true)
        val currentNickname = getCurrentNickname()

        checkNicknameStateUseCase(currentNickname)
            .onSuccess { nicknameState ->
                nicknameState.also { _nicknameState.value = it }
                    .takeIf { it == NicknameState.AVAILABLE }
                    ?.also { validNickname = currentNickname }
            }.onFailure { Log.d("malibindebug", it.stackTrace.toList().toString()) }

        notifyLoading(false)
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

    private fun getCurrentNickname(): String {
        return nickname.value.orEmpty()
    }
}
