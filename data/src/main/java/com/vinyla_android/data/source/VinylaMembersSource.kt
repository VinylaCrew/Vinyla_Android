package com.vinyla_android.data.source

import com.vinyla_android.domain.entity.member.SignUpInfo
import com.vinyla_android.domain.entity.member.nickname.NicknameState

/**
 * Created By Malibin
 * on 10월 09, 2021
 */

internal interface VinylaMembersSource {

    suspend fun checkNickname(nickname: String): NicknameState

    suspend fun signUp(signUpInfo: SignUpInfo): Result<Unit>
}
