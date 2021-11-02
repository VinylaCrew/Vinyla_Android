package com.vinyla_android.fake.remote.repository

import com.vinyla_android.data.source.VinylaMembersSource
import com.vinyla_android.domain.member.NicknameStatus
import com.vinyla_android.domain.member.SignUpInfo
import javax.inject.Inject

/**
 * Created By Malibin
 * on 10월 09, 2021
 */

internal class FakeVinylaMembersSource @Inject constructor() : VinylaMembersSource {

    override suspend fun checkNickname(nickname: String): NicknameStatus {
        throw UnsupportedOperationException("stub!")
    }

    override suspend fun signUp(signUpInfo: SignUpInfo): Result<Unit> {
        throw UnsupportedOperationException("stub!")
    }
}
