package com.vinyla_android.presentation.login.auth

import android.content.Intent
import androidx.fragment.app.FragmentActivity
import com.vinyla_android.data.model.UserProfile

/**
 * Created By Malibin
 * on 4월 12, 2021
 */

interface SnsAuth {

    fun login(activity: FragmentActivity, callback: (UserProfile?) -> Unit)

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
