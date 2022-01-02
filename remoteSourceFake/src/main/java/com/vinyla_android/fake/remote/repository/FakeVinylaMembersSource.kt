package com.vinyla_android.fake.remote.repository

import com.vinyla_android.data.source.VinylaMembersSource
import com.vinyla_android.domain.entity.member.SignUpInfo
import com.vinyla_android.domain.entity.member.nickname.NicknameState
import javax.inject.Inject

/**
 * Created By Malibin
 * on 10월 09, 2021
 */

internal class FakeVinylaMembersSource @Inject constructor() : VinylaMembersSource {

    override suspend fun checkNickname(nickname: String): NicknameState {
        throw UnsupportedOperationException("stub!")
    }

    override suspend fun signUp(signUpInfo: SignUpInfo): Result<Unit> {
        throw UnsupportedOperationException("stub!")
    }
}
