package com.vinyla_android.presentation.login.auth

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.vinyla_android.data.model.UserProfile

/**
 * Created By Malibin
 * on 8월 22, 2021
 */

class GoogleAuth : SnsAuth {

    private val googleSignInOptions: GoogleSignInOptions by lazy { createGoogleSignInOptions() }

    private var loginCallbackProxy: ((UserProfile?) -> Unit)? = null

    private fun createGoogleSignInOptions(): GoogleSignInOptions {
        return GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(getString(R.string.default_web_client_id))
            .requestId()
            .requestEmail()
            .requestProfile()
            .build()
    }

    override fun login(activity: FragmentActivity, callback: (UserProfile?) -> Unit) {
        loginCallbackProxy = callback

        val googleSignInClient = GoogleSignIn.getClient(activity, googleSignInOptions)
        val intent = googleSignInClient.signInIntent
        activity.startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        if (requestCode != REQUEST_CODE || resultCode != Activity.RESULT_OK) return

        val googleAccount: GoogleSignInAccount = GoogleSignIn.getSignedInAccountFromIntent(intent)
            .result
            .takeIf { it != null } ?: return
        loginCallbackProxy?.invoke(createUserProfile(googleAccount))
        loginCallbackProxy = null
    }

    override fun getUserProfile(callback: (UserProfile?) -> Unit) {
    }

    override fun logout(endCallback: (() -> Unit)?) {
    }

    override fun quit(endCallback: (() -> Unit)?) {
    }

    private fun createUserProfile(googleAccount: GoogleSignInAccount): UserProfile? {
        return UserProfile(
            nickname = googleAccount.account?.name ?: return null,
            profileUrl = googleAccount.photoUrl?.toString() ?: return null,
            authType = SnsAuth.Type.GOOGLE,
        )
    }

    companion object {
        const val REQUEST_CODE = 5000
    }
}
