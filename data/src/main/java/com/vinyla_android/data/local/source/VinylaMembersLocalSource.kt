package com.vinyla_android.data.local.source

import com.vinyla_android.data.source.VinylaMembersSource
import com.vinyla_android.domain.member.NicknameStatus
import com.vinyla_android.domain.member.SignUpInfo
import javax.inject.Inject

/**
 * Created By Malibin
 * on 10월 09, 2021
 */

internal class VinylaMembersLocalSource @Inject constructor(

) : VinylaMembersSource {

    override suspend fun checkNickname(nickname: String): NicknameStatus {
        throw UnsupportedOperationException("checkNickname() should not be called in LocalSource")
    }

    override suspend fun signUp(signUpInfo: SignUpInfo): Result<Unit> {
        throw UnsupportedOperationException("signUp() should not be called in LocalSource")
    }
}
