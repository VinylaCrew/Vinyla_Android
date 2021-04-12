package com.vinyla_android.presentation.login.auth

import com.vinyla_android.data.model.UserProfile

/**
 * Created By Malibin
 * on 4월 12, 2021
 */

interface SnsAuth {
    fun login(callback: (UserProfile?) -> Unit)

    fun getUserProfile(callback: (UserProfile?) -> Unit)

    fun logout(endCallback: (() -> Unit)? = null)

    fun quit(endCallback: (() -> Unit)? = null)

    enum class Type {
        KAKAO,
        FACEBOOK,
        APPLE;
    }
}
