package com.vinyla_android.presentation.login.auth

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.ResultReceiver
import com.facebook.*
import com.facebook.login.*
import com.facebook.login.widget.LoginButton
import com.vinyla_android.data.model.UserProfile
import com.vinyla_android.presentation.utils.printLog
import java.util.*

class FacebookAuth(
    private val context: Context,
) : SnsAuth {

    override fun login(callback: (UserProfile?) -> Unit) {
        val callbackManager = CallbackManager.Factory.create();

//        val loginManager = LoginManager.getInstance()
//        loginManager.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
//            override fun onSuccess(result: LoginResult?) {
//                printLog(Profile.getCurrentProfile().toString())
//            }
//
//            override fun onCancel() {
//            }
//
//            override fun onError(error: FacebookException?) {
//            }
//
//        })

        LoginButton(context).apply {
            registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult?) {
                    printLog(result.toString())
                    printLog(Profile.getCurrentProfile().toString())
                }

                override fun onCancel() {
                    printLog("onCancel")
                }

                override fun onError(error: FacebookException?) {
                    printLog("onError")
                }

            })
        }.performClick()


//        loginManager.logIn()

//        val intent = getFacebookActivityIntent()
//
//        context.startActivity(intent)
    }


//    protected fun createLoginRequest(permissions: Collection<String?>?): LoginClient.Request? {
//        val request = LoginClient.Request(
//            loginBehavior,
//            Collections.unmodifiableSet<String?>(
//                if (permissions != null) HashSet<Any?>(permissions) else HashSet()
//            ),
//            defaultAudience,
//            authType,
//            FacebookSdk.getApplicationId(),
//            UUID.randomUUID().toString()
//        )
//        request.setRerequest(AccessToken.isCurrentAccessTokenActive())
//        return request
//    }

    private fun getFacebookActivityIntent(): Intent {
        val intent = Intent()
        intent.setClass(context, FacebookActivity::class.java)
        intent.action = LoginBehavior.NATIVE_WITH_FALLBACK.toString()

        // Let FacebookActivity populate extras appropriately
        val extras = Bundle()
//        extras.putParcelable("request", request)
//        intent.putExtra(LoginFragment.REQUEST_KEY, extras)
        intent.putExtra("result_receiver", createResultReceiver())
        return intent
    }

    private fun createResultReceiver(): ResultReceiver {
        return object : ResultReceiver(Handler(Looper.getMainLooper())) {
            override fun onReceiveResult(resultCode: Int, resultData: Bundle?) {
                if (resultCode == Activity.RESULT_OK) {
                    // callback code
                    printLog("resultData : $resultData")
                }
            }
        }
    }

    override fun getUserProfile(callback: (UserProfile?) -> Unit) {

    }

    override fun logout(endCallback: (() -> Unit)?) {
        LoginManager.getInstance().logOut()
    }

    override fun quit(endCallback: (() -> Unit)?) {
    }
}
