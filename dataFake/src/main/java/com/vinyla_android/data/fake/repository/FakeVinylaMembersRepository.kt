package com.vinyla_android.data.fake.repository

import com.vinyla_android.domain.entity.member.SignUpInfo
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

    override suspend fun signUp(signUpInfo: SignUpInfo): Result<Unit> {
        return Result.success(Unit)
    }

    override suspend fun getFcmToken(): Result<String> {
        TODO("Not yet implemented")
    }

    override suspend fun saveFcmToken(fcmToken: String) {
        TODO("Not yet implemented")
    }
}
