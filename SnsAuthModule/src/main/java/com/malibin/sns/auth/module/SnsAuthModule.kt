package com.malibin.sns.auth.module

import android.content.Intent
import androidx.fragment.app.FragmentActivity
import com.malibin.sns.auth.model.UserProfile

/**
 * Created By Malibin
 * on 4월 12, 2021
 */


interface SnsAuthModule {
    fun login(activity: FragmentActivity, callback: (UserProfile?) -> Unit)

    fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        /** onActivityResults 콜백을 받아야만 하는 녀석들을 위한 메서드 */
    }

    fun getUserProfile(callback: (UserProfile?) -> Unit)

    fun logout(endCallback: (() -> Unit)? = null)

    fun unlink(endCallback: (() -> Unit)? = null)
}
