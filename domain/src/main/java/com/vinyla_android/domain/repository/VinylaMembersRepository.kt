package com.vinyla_android.domain.repository

import com.vinyla_android.domain.entity.member.SignUpInfo
import com.vinyla_android.domain.entity.member.nickname.NicknameState

/**
 * Created By Malibin
 * on 10월 09, 2021
 */

interface VinylaMembersRepository {

    suspend fun checkNickname(nickname: String): Result<NicknameState>

    suspend fun signUp(signUpInfo: SignUpInfo): Result<Unit>

    suspend fun getFcmToken(): Result<String>

    suspend fun saveFcmToken(fcmToken: String)
}
