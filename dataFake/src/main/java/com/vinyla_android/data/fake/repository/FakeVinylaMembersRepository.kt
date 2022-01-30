package com.vinyla_android.data.fake.repository

import com.vinyla_android.domain.entity.member.SignUpInfo
import com.vinyla_android.domain.entity.member.SnsType
import com.vinyla_android.domain.entity.member.VinylaToken
import com.vinyla_android.domain.entity.member.nickname.NicknameState
import com.vinyla_android.domain.repository.VinylaMembersRepository
import javax.inject.Inject

internal class FakeVinylaMembersRepository @Inject constructor() : VinylaMembersRepository {

    override suspend fun checkNickname(nickname: String): Result<NicknameState> {
        return when (nickname) {
            "malibin" -> Result.success(NicknameState.AVAILABLE)
            "mome" -> Result.success(NicknameState.DUPLICATED)
            else -> Result.success(NicknameState.INVALID)
        }
    }

    override suspend fun signUp(signUpInfo: SignUpInfo): Result<VinylaToken> {
        return Result.success(VinylaToken(""))
    }

    override suspend fun getFcmToken(): Result<String> {
        TODO("Not yet implemented")
    }

    override suspend fun saveFcmToken(fcmToken: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getMarketingAgreed(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun saveMarketingAgreed(isAgreed: Boolean) {
        TODO("Not yet implemented")
    }

    override suspend fun getNickname(): String? {
        TODO("Not yet implemented")
    }

    override suspend fun saveNickname(nickname: String) {
        TODO("Not yet implemented")
    }

    override suspend fun saveLoginSnsType(type: SnsType) {
        TODO("Not yet implemented")
    }

    override suspend fun getLoginSnsType(): SnsType? {
        TODO("Not yet implemented")
    }

    override suspend fun getVinylaToken(): VinylaToken? {
        TODO("Not yet implemented")
    }

    override suspend fun saveVinylaToken(token: String) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteVinylaToken() {
        TODO("Not yet implemented")
    }
}
