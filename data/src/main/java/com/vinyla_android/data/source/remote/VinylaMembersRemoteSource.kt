package com.vinyla_android.data.source.remote

import com.vinyla_android.data.remote.params.CheckNicknameParams
import com.vinyla_android.data.remote.params.SignUpParams
import com.vinyla_android.data.remote.service.VinylaService
import com.vinyla_android.data.source.VinylaMembersSource
import com.vinyla_android.domain.entity.member.SignUpInfo
import com.vinyla_android.domain.entity.member.SnsType
import com.vinyla_android.domain.entity.member.VinylaToken
import com.vinyla_android.domain.entity.member.nickname.NicknameState
import javax.inject.Inject

/**
 * Created By Malibin
 * on 10월 09, 2021
 */

internal class VinylaMembersRemoteSource @Inject constructor(
    private val vinylaService: VinylaService,
) : VinylaMembersSource {

    //아예 interceptor에서 가공해서 response를 가져오는것도 나쁘지 않지 않을가.
    override suspend fun checkNickname(nickname: String): Result<NicknameState> {
        runCatching { vinylaService.checkNickname(CheckNicknameParams(nickname)) }
            .onSuccess {
                return if (it.isSuccessful) Result.success(NicknameState.AVAILABLE)
                else Result.success(NicknameState.DUPLICATED)
            }.onFailure {
                return Result.failure(IllegalStateException(it.message))
            }
        return Result.failure(IllegalStateException("unknownServerException"))
    }

    override suspend fun signUp(signUpInfo: SignUpInfo): Result<VinylaToken> {
        val response = vinylaService.signUp(SignUpParams.from(signUpInfo))
        val vinylaToken = VinylaToken(
            response.body()?.token ?: return Result.failure(IllegalStateException("회원가입 실패"))
        )
        return if (response.isSuccessful) Result.success(vinylaToken)
        else Result.failure(IllegalStateException("회원가입 실패"))
    }

    override suspend fun getFcmToken(): Result<String> {
        throw UnsupportedOperationException("getFcmToken() should not be called in RemoteSource")
    }

    override suspend fun saveFcmToken(fcmToken: String) {
        throw UnsupportedOperationException("saveFcmToken() should not be called in RemoteSource")
    }

    override suspend fun getMarketingAgreed(): Boolean {
        throw UnsupportedOperationException("getMarketingAgreed() should not be called in RemoteSource")
    }

    override suspend fun saveMarketingAgreed(isAgreed: Boolean) {
        throw UnsupportedOperationException("setMarketingAgreed() should not be called in RemoteSource")
    }

    override suspend fun getNickname(): String? {
        throw UnsupportedOperationException("getNickname() should not be called in RemoteSource")
    }

    override suspend fun saveNickname(nickname: String) {
        throw UnsupportedOperationException("saveNickname() should not be called in RemoteSource")
    }

    override suspend fun getLoginSnsType(): SnsType? {
        throw UnsupportedOperationException("getLoginSnsType() should not be called in RemoteSource")
    }

    override suspend fun saveLoginSnsType(type: SnsType) {
        throw UnsupportedOperationException("saveLoginSnsType() should not be called in RemoteSource")
    }

    override suspend fun getVinylaToken(): VinylaToken? {
        throw UnsupportedOperationException("getVinylaToken() should not be called in RemoteSource")
    }

    override suspend fun saveVinylaToken(token: String) {
        throw UnsupportedOperationException("saveVinylaToken() should not be called in RemoteSource")
    }

    override suspend fun deleteVinylaToken() {
        throw UnsupportedOperationException("deleteVinylaToken() should not be called in RemoteSource")
    }
}
