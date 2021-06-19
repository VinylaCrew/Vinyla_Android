package com.malibin.sns.auth

import android.content.Intent

/**
 * Created By Malibin
 * on 4월 12, 2021
 */

interface SnsAuth {
    fun login(callback: (UserProfile?) -> Unit)

    fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        /** onActivityResults 콜백을 받아야만 하는 녀석들을 위한 메서드 */
    }

    fun getUserProfile(callback: (UserProfile?) -> Unit)

    fun logout(endCallback: (() -> Unit)? = null)

    fun quit(endCallback: (() -> Unit)? = null)

    enum class Type {
        KAKAO,
        FACEBOOK,
        APPLE;
    }
}
