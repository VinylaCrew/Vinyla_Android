package com.malibin.sns.auth

import android.content.Intent
import androidx.fragment.app.FragmentActivity
import com.malibin.sns.auth.model.SnsType
import com.malibin.sns.auth.model.UserProfile
import com.malibin.sns.auth.module.SnsAuthModules
import com.malibin.sns.auth.module.facebook.FacebookAuth
import com.malibin.sns.auth.module.google.GoogleAuth

/**
 * Created By Malibin
 * on 4월 12, 2021
 */

class SnsAuth private constructor(
    private val snsAuthModules: SnsAuthModules
) {
    fun login(type: SnsType, activity: FragmentActivity, callback: (UserProfile?) -> Unit) {
        snsAuthModules[type].login(activity, callback)
    }

    fun getUserProfile(type: SnsType, callback: (UserProfile?) -> Unit) {
        snsAuthModules[type].getUserProfile(callback)
    }

    fun logout(type: SnsType, endCallback: (() -> Unit)? = null) {
        snsAuthModules[type].logout(endCallback)
    }

    fun unlink(type: SnsType, endCallback: (() -> Unit)? = null) {
        snsAuthModules[type].unlink(endCallback)
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) = when (requestCode) {
        REQUEST_CODE_FACEBOOK -> snsAuthModules[SnsType.FACEBOOK]
            .onActivityResult(requestCode, resultCode, intent)
        else -> Unit
    }

    companion object {
        /**
         * From FaceBook SDK
         * CallbackManagerImpl.RequestCodeOffset.Login.toRequestCode()
         * DEFAULT_CALLBACK_REQUEST_CODE_OFFSET(0xface) + Login(0)
         */
        private const val REQUEST_CODE_FACEBOOK = 0xface + 0

        private var instance: SnsAuth? = null

//        fun getInstance(): SnsAuth = instance
//            ?: error("initSnsLogin is missing. check \"initSnsLogin\" on Application")

        @Deprecated("임시로 만든 것 리팩터링 할 것")
        fun getInstance(): SnsAuth {
            val modules = SnsAuthModules(
                mapOf(
                    SnsType.FACEBOOK to FacebookAuth(),
                    SnsType.GOOGLE to GoogleAuth(),
                )
            )
            return SnsAuth(modules).also { instance = it }
        }

        internal fun initialize(snsAuthModules: SnsAuthModules) {
            synchronized(this) {
                if (instance != null) return
                instance = SnsAuth(snsAuthModules)
            }
        }
    }
}
