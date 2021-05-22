package com.vinyla_android.presentation.signup

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(

) : ViewModel() {
    val nickname = MutableLiveData("")
    val instagramId = MutableLiveData("")

    val isTermsAndConditionOfServiceChecked = MutableLiveData(false)
    val isPrivacyPolicyChecked = MutableLiveData(false)
    val isMarketingChecked = MutableLiveData(false)

    val isAllChecked = MediatorLiveData<Boolean>().apply {
        addSource(isTermsAndConditionOfServiceChecked) { checkAllChecked() }
        addSource(isPrivacyPolicyChecked) { checkAllChecked() }
        addSource(isMarketingChecked) { checkAllChecked() }
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
}
