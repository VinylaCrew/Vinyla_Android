package com.vinyla_android.data.source

import com.vinyla_android.domain.member.NicknameStatus
import com.vinyla_android.domain.member.SignUpInfo

/**
 * Created By Malibin
 * on 10월 09, 2021
 */

interface VinylaMembersSource {

    suspend fun checkNickname(nickname: String): NicknameStatus

    suspend fun signUp(signUpInfo: SignUpInfo): Result<Unit>
}
