package com.vinyla_android.data.remote.service.params

import com.vinyla_android.domain.member.SignUpInfo

/**
 * Created By Malibin
 * on 10월 10, 2021
 */

internal data class SignUpParams(
    val nickname: String,
    val profileUrl: String,
    val instaId: String,
) {
    companion object {
        fun from(signUpInfo: SignUpInfo): SignUpParams = SignUpParams(
            nickname = signUpInfo.nickname,
            profileUrl = signUpInfo.profileImageUrl,
            instaId = signUpInfo.instagramId,
        )
    }
}
