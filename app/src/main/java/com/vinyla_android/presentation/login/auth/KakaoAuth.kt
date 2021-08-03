package com.vinyla_android.presentation.login.auth

import androidx.fragment.app.FragmentActivity
import com.kakao.sdk.user.UserApiClient
import com.kakao.sdk.user.model.User
import com.vinyla_android.data.model.UserProfile

/**
 * Created By Malibin
 * on 4월 12, 2021
 */

class KakaoAuth : SnsAuth {

    override fun login(activity: FragmentActivity, callback: (UserProfile?) -> Unit) {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(activity)) {
            UserApiClient.instance.loginWithKakaoTalk(activity) { _, loginError ->
                loginError?.printStackTrace()
                UserApiClient.instance.me { user, error ->
                    callback(user.toUserProfile())
                    error?.printStackTrace()
                }
            }
            return
        }
        UserApiClient.instance.loginWithKakaoAccount(activity) { _, loginError ->
            loginError?.printStackTrace()
            UserApiClient.instance.me { user, error ->
                callback(user.toUserProfile())
                error?.printStackTrace()
            }
        }
    }

    private fun User?.toUserProfile(): UserProfile? {
        return UserProfile(
            nickname = this?.kakaoAccount?.profile?.nickname ?: return null,
            profileUrl = this.kakaoAccount?.profile?.thumbnailImageUrl ?: return null,
            authType = SnsAuth.Type.KAKAO,
        )
    }

    override fun getUserProfile(callback: (UserProfile?) -> Unit) {
        UserApiClient.instance.me { user, error ->
            callback(user.toUserProfile())
            error?.printStackTrace()
        }
    }

    override fun logout(endCallback: (() -> Unit)?) {
        UserApiClient.instance.logout {
            endCallback?.invoke()
            it?.printStackTrace()
        }
    }

    override fun quit(endCallback: (() -> Unit)?) {
        UserApiClient.instance.unlink {
            endCallback?.invoke()
            it?.printStackTrace()
        }
    }
}
