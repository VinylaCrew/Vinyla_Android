package com.vinyla_android.presentation.login.auth

import android.content.Context
import android.content.Intent
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.vinyla_android.data.model.UserProfile
import com.vinyla_android.presentation.utils.printLog

class FacebookAuth(
    private val context: Context,
) : SnsAuth {
    private val callbackManager: CallbackManager = CallbackManager.Factory.create()

    override fun login(callback: (UserProfile?) -> Unit) {
        if (AccessToken.getCurrentAccessToken()?.isExpired == false) {
            callback(createUserProfile())
            return
        }
        LoginButton(context).apply {
            registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult?) {
                    printLog("Facebook Auth onSuccess")
                    callback(createUserProfile())
                }

                override fun onCancel() {
                    printLog("Facebook Auth onCancel")
                    callback(null)
                }

                override fun onError(error: FacebookException?) {
                    printLog("Facebook Auth onError")
                    callback(null)
                }
            })
        }.performClick()
    }

    private fun createUserProfile(): UserProfile? {
        val facebookProfile = Profile.getCurrentProfile() ?: return null
        return UserProfile(
            nickname = facebookProfile.name,
            profileUrl = facebookProfile.getProfilePictureUri(500, 500).toString(),
            authType = SnsAuth.Type.FACEBOOK,
        )
    }

    /**
     * 페이스북 인증은 이 거지같은 callbackManager.onActivityResult 메서드를 호출하지 않으면
     * 내부적으로 로그인이후 callback 관련된 것들이 호출되지 않는다.
     *
     * 이 의존성을 떼내려고
     * 1. 직접 Facebook Activity를 띄운다
     *      * 이렇게 하기 위해서는 intent를 직접 만들어 주어야하는데, 이 때 필요한 Request라던지
     *      * 필요한 항목들이 모두 package private로 접근 또는 직접 생성이 불가능했다.
     *
     * 2. Kakao에서 쓰던 방식인 ResultReceiver를 활용한다.
     *      * 하지만 이놈은 Parcelable 객체이며, result를 받기 위한 액티비티로 직접 넘겨줘야한다.
     *      * 그리고 해당 액티비티에서 result를 넘겨주는 코드가 작성되어야만 한다.
     *
     * 이런 이유로 결국 아래 onActivityResult 메서드를 추가시켰다. (SnsAuth)
     * Kakao는 필요없으므로 반드시 구현할 필요가 없는 Default Method로 선언했다.
     *
     * RequestCode는 SnsAuthManager에서 직접 판별한다.
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, intent)
    }

    override fun getUserProfile(callback: (UserProfile?) -> Unit) {
        callback(createUserProfile())
    }

    override fun logout(endCallback: (() -> Unit)?) {
        LoginManager.getInstance().logOut()
    }

    override fun quit(endCallback: (() -> Unit)?) {
        //TODO: 서버 직접 통신으로 끊어야함
    }
}
