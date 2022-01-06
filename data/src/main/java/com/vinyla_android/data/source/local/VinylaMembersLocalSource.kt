package com.vinyla_android.data.source.local

import com.vinyla_android.data.source.VinylaMembersSource
import com.vinyla_android.domain.entity.member.SignUpInfo
import com.vinyla_android.domain.entity.member.nickname.NicknameState
import javax.inject.Inject

/**
 * Created By Malibin
 * on 10월 09, 2021
 */

internal class VinylaMembersLocalSource @Inject constructor(
) : VinylaMembersSource {

    override suspend fun checkNickname(nickname: String): NicknameState {
        throw UnsupportedOperationException("checkNickname() should not be called in LocalSource")
    }

    override suspend fun signUp(signUpInfo: SignUpInfo): Result<Unit> {
        throw UnsupportedOperationException("signUp() should not be called in LocalSource")
    }
}