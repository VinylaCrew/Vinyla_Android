package com.vinyla_android.data.model

import com.vinyla_android.presentation.login.auth.SnsAuth

/**
 * Created By Malibin
 * on 4월 12, 2021
 */

data class UserProfile(
    val nickname: String,
    val profileUrl: String,
    val authType: SnsAuth.Type,
)
