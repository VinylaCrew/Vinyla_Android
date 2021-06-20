package com.malibin.sns.auth.module.google

import com.malibin.sns.auth.module.SnsAuthModule
import com.malibin.sns.auth.model.UserProfile

class GoogleAuth : SnsAuthModule {
    override fun login(callback: (UserProfile?) -> Unit) {

    }

    override fun getUserProfile(callback: (UserProfile?) -> Unit) {

    }

    override fun logout(endCallback: (() -> Unit)?) {

    }

    override fun unlink(endCallback: (() -> Unit)?) {

    }
}
