package com.vinyla_android.presentation.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.malibin.sns.auth.model.SnsType
import com.malibin.sns.auth.model.UserProfile
import com.vinyla_android.domain.entity.member.nickname.NicknameState
import com.vinyla_android.domain.event.SingleLiveEvent
import com.vinyla_android.domain.usecase.member.CheckNicknameStateUseCase
import com.vinyla_android.domain.usecase.member.SignUpUseCase
import com.vinyla_android.presentation.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val checkNicknameStateUseCase: CheckNicknameStateUseCase,
    private val signUpUseCase: SignUpUseCase,
) : BaseViewModel() {
    private var validNickname: String = ""
    val nickname = MutableLiveData("")
    val instagramId = MutableLiveData("")

    private val _isSignUpSuccess = SingleLiveEvent<Unit>()
    val isSignUpSuccess: LiveData<Unit> = _isSignUpSuccess

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

    private lateinit var profile: UserProfile

    fun loadProfile(profile: UserProfile) {
        this.profile = profile
        this.nickname.value = profile.nickname
    }

    fun checkNicknameFormat() = viewModelScope.launch {
        notifyLoading(true)
        val currentNickname = nickname.value.orEmpty()

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

    fun signUp() = viewModelScope.launch {
        notifyLoading(true)
        signUpUseCase(
            firebaseUid = profile.firebaseUniqueToken,
            nickname = validNickname,
            instagramId = instagramId.value.orEmpty(),
            profileImageUrl = profile.profileUrl,
            snsType = when (profile.authType) {
                SnsType.FACEBOOK -> com.vinyla_android.domain.entity.member.SnsType.FACEBOOK
                SnsType.GOOGLE -> com.vinyla_android.domain.entity.member.SnsType.GOOGLE
                else -> com.vinyla_android.domain.entity.member.SnsType.APPLE
            },
            marketingAgreed = isMarketingChecked.value ?: false,
        ).onSuccess {
            _isSignUpSuccess.value = Unit
        }.onFailure {
            notifyToastMessage(it.message)
        }
        notifyLoading(false)
    }
}
