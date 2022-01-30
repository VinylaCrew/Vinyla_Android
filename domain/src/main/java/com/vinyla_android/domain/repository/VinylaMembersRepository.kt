package com.vinyla_android.domain.repository

import com.vinyla_android.domain.entity.member.SignUpInfo
import com.vinyla_android.domain.entity.member.SnsType
import com.vinyla_android.domain.entity.member.VinylaToken
import com.vinyla_android.domain.entity.member.nickname.NicknameState

/**
 * Created By Malibin
 * on 10월 09, 2021
 */

interface VinylaMembersRepository {

    suspend fun checkNickname(nickname: String): Result<NicknameState>

    suspend fun signUp(signUpInfo: SignUpInfo): Result<VinylaToken>

    suspend fun getFcmToken(): Result<String>

    suspend fun saveFcmToken(fcmToken: String)

    suspend fun getMarketingAgreed(): Boolean

    suspend fun saveMarketingAgreed(isAgreed: Boolean)

    suspend fun getNickname(): String?

    suspend fun saveNickname(nickname: String)

    suspend fun saveLoginSnsType(type: SnsType)

    suspend fun getLoginSnsType(): SnsType?

    suspend fun getVinylaToken(): VinylaToken?

    suspend fun saveVinylaToken(token: String)

    suspend fun deleteVinylaToken()
}
