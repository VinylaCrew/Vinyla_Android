package com.vinyla_android.data.fake.repository

import com.vinyla_android.domain.entity.member.SignUpInfo
import com.vinyla_android.domain.entity.member.nickname.NicknameState
import com.vinyla_android.domain.repository.VinylaMembersRepository

internal class FakeVinylaMembersRepository : VinylaMembersRepository {

    override suspend fun checkNickname(nickname: String): NicknameState {
        return when (nickname) {
            "malibin" -> NicknameState.AVAILABLE
            "mome" -> NicknameState.DUPLICATED
            else -> NicknameState.INVALID
        }
    }

    override suspend fun signUp(signUpInfo: SignUpInfo): Result<Unit> {
        return Result.success(Unit)
    }
}
