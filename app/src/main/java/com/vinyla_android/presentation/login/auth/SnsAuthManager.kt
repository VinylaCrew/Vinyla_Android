package com.vinyla_android.presentation.login.auth

import android.content.Context
import android.content.Intent
import com.vinyla_android.data.model.UserProfile

/**
 * Created By Malibin
 * on 4월 12, 2021
 */

class SnsAuthManager(
    private val kakaoAuth: SnsAuth,
    private val facebookAuth: SnsAuth,
    private val appleAuth: SnsAuth,
) {
    constructor(context: Context) : this(
        kakaoAuth = KakaoAuth(context),
        facebookAuth = FacebookAuth(context),
        appleAuth = KakaoAuth(context),
    )

    fun login(type: SnsAuth.Type, callback: (UserProfile?) -> Unit) {
        getSnsAuth(type).login(callback)
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
        REQUEST_CODE_FACEBOOK -> facebookAuth.onActivityResult(requestCode, resultCode, intent)
        else -> Unit
    }

    private fun getSnsAuth(type: SnsAuth.Type): SnsAuth = when (type) {
        SnsAuth.Type.KAKAO -> kakaoAuth
        SnsAuth.Type.FACEBOOK -> facebookAuth
        SnsAuth.Type.APPLE -> appleAuth
    }

    companion object {
        /**
         * CallbackManagerImpl.RequestCodeOffset.Login.toRequestCode()
         * DEFAULT_CALLBACK_REQUEST_CODE_OFFSET(0xface) + Login(0)
         */
        private const val REQUEST_CODE_FACEBOOK = 0xface + 0
    }
}
