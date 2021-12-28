package com.vinyla_android.domain.repository

import com.vinyla_android.domain.entity.member.nickname.NicknameStatus
import com.vinyla_android.domain.entity.member.SignUpInfo

/**
 * Created By Malibin
 * on 10월 09, 2021
 */

interface VinylaMembersRepository {

    suspend fun checkNickname(nickname: String): NicknameStatus

    suspend fun signUp(signUpInfo: SignUpInfo): Result<Unit>
}
