package com.vinyla_android.data.repository

import com.vinyla_android.data.di.annotation.VinylaMembersLocal
import com.vinyla_android.data.di.annotation.VinylaMembersRemote
import com.vinyla_android.data.source.VinylaMembersSource
import com.vinyla_android.domain.entity.member.nickname.NicknameState
import com.vinyla_android.domain.entity.member.SignUpInfo
import com.vinyla_android.domain.repository.VinylaMembersRepository
import javax.inject.Inject

/**
 * Created By Malibin
 * on 10월 09, 2021
 */

internal class RealVinylaMembersRepository @Inject constructor(
    @VinylaMembersLocal private val vinylaMembersLocalSource: VinylaMembersSource,
    @VinylaMembersRemote private val vinylaMembersRemoteSource: VinylaMembersSource,
) : VinylaMembersRepository {

    override suspend fun checkNickname(nickname: String): NicknameState {
        return vinylaMembersRemoteSource.checkNickname(nickname)
    }

    override suspend fun signUp(signUpInfo: SignUpInfo): Result<Unit> {
        return vinylaMembersRemoteSource.signUp(signUpInfo)
    }
}
