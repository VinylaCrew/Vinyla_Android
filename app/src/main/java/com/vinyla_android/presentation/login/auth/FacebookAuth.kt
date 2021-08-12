package com.vinyla_android.presentation.login.auth

import android.content.Intent
import androidx.fragment.app.FragmentActivity
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.vinyla_android.data.remote.interceptor.HTTP_LOGGING_INTERCEPTOR
import com.vinyla_android.data.model.UserProfile
import com.vinyla_android.data.remote.service.FacebookAuthService
import com.vinyla_android.presentation.utils.printLog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FacebookAuth : SnsAuth {
    private val callbackManager: CallbackManager = CallbackManager.Factory.create()
    private var profileTracker: ProfileTracker? = null

    private val facebookAuthService: FacebookAuthService by lazy { createFacebookAuthService() }

    /**
     * onSuccess 이후에 바로 Callback을 호출 하지 않는 이유
     *      FacebookCallback은 callbackManager.onActivityResult를 호출하지 않으면 호출되지 않는다.
     *      onActivityResult 이후 내부 Profile 객체를 초기화 시키고, FacebookCallback을 호출한다.
     *      위에서 Profile 객체 초기화 로직이 비동기로 돈다.
     *      그래서 onSuccess 호출이 되어도 Profile은 null이 리턴 되는 경우가 생긴다.
     *      그렇기 때문에 profileTracker를 사용했다.
     *      로직 개복잡하네 ㄹㅇ Facebook SDK 별로인듯
     */
    override fun login(activity: FragmentActivity, callback: (UserProfile?) -> Unit) {
        if (AccessToken.getCurrentAccessToken()?.isExpired == false) {
            printLog("Facebook AccessToken is not expried")
            callback(createUserProfile())
            return
        }
        val loginButton = LoginButton(activity)
        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                printLog("Facebook Auth onSuccess")

                profileTracker = object : ProfileTracker() {
                    override fun onCurrentProfileChanged(
                        oldProfile: Profile?,
                        currentProfile: Profile?
                    ) {
                        callback(createUserProfile(currentProfile))
                        stopProfileTracker()
                    }
                }
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
        loginButton.performClick()
    }

    /**
     * 내부적으로 BroadCastReceiver를 생성하고 사용하기 때문에 멈춰줘야한다.
     */
    private fun stopProfileTracker() {
        profileTracker?.stopTracking()
        profileTracker = null
    }

    private fun createUserProfile(profile: Profile? = null): UserProfile? {
        val facebookProfile = profile ?: Profile.getCurrentProfile() ?: return null
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
        endCallback?.invoke()
    }

    override fun quit(endCallback: (() -> Unit)?) {
        val id = Profile.getCurrentProfile()?.id.orEmpty()
        val accessToken = AccessToken.getCurrentAccessToken()?.token.orEmpty()
        CoroutineScope(Dispatchers.IO).launch {
            logout()
            val response = facebookAuthService.unLink(id, accessToken)
            if (!response.isSuccessful) {
                printLog("Response Error Body : ${response.errorBody()?.string()}")
            }
            endCallback?.invoke()
        }
    }

    private fun createFacebookAuthService(): FacebookAuthService {
        return Retrofit.Builder()
            .baseUrl(FacebookAuthService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HTTP_LOGGING_INTERCEPTOR)
                    .build()
            )
            .build()
            .create(FacebookAuthService::class.java)
    }

    companion object {
        /**
         * CallbackManagerImpl.RequestCodeOffset.Login.toRequestCode()
         * DEFAULT_CALLBACK_REQUEST_CODE_OFFSET(0xface) + Login(0)
         */
        const val REQUEST_CODE = 0xface + 0
    }
}
