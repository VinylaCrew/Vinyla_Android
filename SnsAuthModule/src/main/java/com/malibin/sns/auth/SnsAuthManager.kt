package com.malibin.sns.auth

import android.content.Intent

/**
 * Created By Malibin
 * on 4월 12, 2021
 */

class SnsAuthManager private constructor(
    private val snsAuthHolder: SnsAuthHolder
) {
    fun login(type: SnsType, callback: (UserProfile?) -> Unit) {
        findSnsAuth(type).login(callback)
    }

    fun getUserProfile(type: SnsType, callback: (UserProfile?) -> Unit) {
        findSnsAuth(type).getUserProfile(callback)
    }

    fun logout(type: SnsType, endCallback: (() -> Unit)? = null) {
        findSnsAuth(type).logout(endCallback)
    }

    fun unlink(type: SnsType, endCallback: (() -> Unit)? = null) {
        findSnsAuth(type).unlink(endCallback)
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) = when (requestCode) {
        REQUEST_CODE_FACEBOOK -> findSnsAuth(SnsType.FACEBOOK)
            .onActivityResult(requestCode, resultCode, intent)
        else -> Unit
    }

    private fun findSnsAuth(type: SnsType): SnsAuth = snsAuthHolder.find(type)

    companion object {
        /**
         * From FaceBook SDK
         * CallbackManagerImpl.RequestCodeOffset.Login.toRequestCode()
         * DEFAULT_CALLBACK_REQUEST_CODE_OFFSET(0xface) + Login(0)
         */
        private const val REQUEST_CODE_FACEBOOK = 0xface + 0

        private var instance: SnsAuthManager? = null

        fun getInstance(): SnsAuthManager = instance
            ?: error("initSnsLogin is missing. check \"initSnsLogin\" on Application")

        internal fun initialize(snsAuthHolder: SnsAuthHolder) {
            instance = SnsAuthManager(snsAuthHolder)
        }
    }
}
