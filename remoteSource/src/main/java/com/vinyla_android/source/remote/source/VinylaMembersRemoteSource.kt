package com.vinyla_android.source.remote.source

import com.vinyla_android.data.source.VinylaMembersSource
import com.vinyla_android.domain.entity.member.SignUpInfo
import com.vinyla_android.domain.entity.member.nickname.NicknameState
import com.vinyla_android.source.remote.params.CheckNicknameParams
import com.vinyla_android.source.remote.params.SignUpParams
import com.vinyla_android.source.remote.service.VinylaService
import javax.inject.Inject

/**
 * Created By Malibin
 * on 10월 09, 2021
 */

internal class VinylaMembersRemoteSource @Inject constructor(
    private val vinylaService: VinylaService,
) : VinylaMembersSource {

    override suspend fun checkNickname(nickname: String): NicknameState {
        val response = vinylaService.checkNickname(CheckNicknameParams(nickname))
        if (response.isSuccessful) return NicknameState.AVAILABLE
        return NicknameState.DUPLICATED
    }

    override suspend fun signUp(signUpInfo: SignUpInfo): Result<Unit> {
        val response = vinylaService.signUp(SignUpParams.from(signUpInfo))
        return if (response.isSuccessful) Result.success(Unit)
        else Result.failure(IllegalStateException("회원가입 실패"))
    }
}
