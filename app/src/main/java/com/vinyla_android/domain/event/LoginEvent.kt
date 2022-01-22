package com.vinyla_android.domain.event

import com.malibin.sns.auth.model.UserProfile

sealed class LoginEvent {
    class SignupNeeded(val userProfile: UserProfile) : LoginEvent()
    object Success : LoginEvent()
    object Fail : LoginEvent()
}
