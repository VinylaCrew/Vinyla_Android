package com.vinyla_android.data.repository

import com.vinyla_android.data.di.annotation.VinylaMembersLocal
import com.vinyla_android.data.di.annotation.VinylaMembersRemote
import com.vinyla_android.data.source.VinylaMembersSource
import com.vinyla_android.domain.entity.member.SignUpInfo
import com.vinyla_android.domain.entity.member.SnsType
import com.vinyla_android.domain.entity.member.VinylaToken
import com.vinyla_android.domain.entity.member.nickname.NicknameState
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

    override suspend fun checkNickname(nickname: String): Result<NicknameState> {
        return vinylaMembersRemoteSource.checkNickname(nickname)
    }

    override suspend fun signUp(signUpInfo: SignUpInfo): Result<VinylaToken> {
        return vinylaMembersRemoteSource.signUp(signUpInfo)
    }

    override suspend fun getFcmToken(): Result<String> {
        return vinylaMembersLocalSource.getFcmToken()
    }

    override suspend fun saveFcmToken(fcmToken: String) {
        vinylaMembersLocalSource.saveFcmToken(fcmToken)
    }

    override suspend fun getMarketingAgreed(): Boolean {
        return vinylaMembersLocalSource.getMarketingAgreed()
    }

    override suspend fun saveMarketingAgreed(isAgreed: Boolean) {
        vinylaMembersLocalSource.saveMarketingAgreed(isAgreed)
    }

    override suspend fun getNickname(): String? {
        return vinylaMembersLocalSource.getNickname()
    }

    override suspend fun saveNickname(nickname: String) {
        vinylaMembersLocalSource.saveNickname(nickname)
    }

    override suspend fun saveLoginSnsType(type: SnsType) {
        vinylaMembersLocalSource.saveLoginSnsType(type)
    }

    override suspend fun getLoginSnsType(): SnsType? {
        return vinylaMembersLocalSource.getLoginSnsType()
    }

    override suspend fun getVinylaToken(): VinylaToken? {
        return vinylaMembersLocalSource.getVinylaToken()
    }

    override suspend fun saveVinylaToken(token: String) {
        vinylaMembersLocalSource.saveVinylaToken(token)
    }

    override suspend fun deleteVinylaToken() {
        vinylaMembersLocalSource.deleteVinylaToken()
    }
}
