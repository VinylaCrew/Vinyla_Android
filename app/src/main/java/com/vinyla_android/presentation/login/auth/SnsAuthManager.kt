package com.vinyla_android.presentation.login.auth

import android.content.Intent
import androidx.fragment.app.FragmentActivity
import com.vinyla_android.data.model.UserProfile

/**
 * Created By Malibin
 * on 4월 12, 2021
 */

class SnsAuthManager(
    private val kakaoAuth: SnsAuth = KakaoAuth(),
    private val facebookAuth: SnsAuth = FacebookAuth(),
    private val appleAuth: SnsAuth = KakaoAuth(),
) {

    fun login(activity: FragmentActivity, type: SnsAuth.Type, callback: (UserProfile?) -> Unit) {
        getSnsAuth(type).login(activity, callback)
    }

    fun getUserProfile(type: SnsAuth.Type, callback: (UserProfile?) -> Unit) {
        getSnsAuth(type).getUserProfile(callback)
    }

    fun logout(type: SnsAuth.Type, endCallback: (() -> Unit)? = null) {
        getSnsAuth(type).logout(endCallback)
    }

    fun quit(type: SnsAuth.Type, endCallback: (() -> Unit)? = null) {
        getSnsAuth(type).quit(endCallback)
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) = when (requestCode) {
        FacebookAuth.REQUEST_CODE -> facebookAuth.onActivityResult(requestCode, resultCode, intent)
        else -> Unit
    }

    private fun getSnsAuth(type: SnsAuth.Type): SnsAuth = when (type) {
        SnsAuth.Type.KAKAO -> kakaoAuth
        SnsAuth.Type.FACEBOOK -> facebookAuth
        SnsAuth.Type.APPLE -> appleAuth
        SnsAuth.Type.GOOGLE -> appleAuth
    }
}
