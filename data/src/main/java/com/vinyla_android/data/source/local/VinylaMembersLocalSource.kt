package com.vinyla_android.data.source.local

import com.vinyla_android.data.local.datastore.DataStoreManager
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

internal class VinylaMembersLocalSource @Inject constructor(
    private val datastore: DataStoreManager,
) : VinylaMembersSource {

    override suspend fun checkNickname(nickname: String): Result<NicknameState> {
        throw UnsupportedOperationException("checkNickname() should not be called in LocalSource")
    }

    override suspend fun signUp(signUpInfo: SignUpInfo): Result<VinylaToken> {
        throw UnsupportedOperationException("signUp() should not be called in LocalSource")
    }

    override suspend fun getFcmToken(): Result<String> {
        val localFcmToken = datastore.getString(KEY_FCM_TOKEN)
            ?: return Result.failure(IllegalStateException("local fcm token not exist"))
        return Result.success(localFcmToken)
    }

    override suspend fun saveFcmToken(fcmToken: String) {
        datastore.saveString(KEY_FCM_TOKEN, fcmToken)
    }

    override suspend fun getMarketingAgreed(): Boolean {
        return datastore.getBoolean(KEY_MARKETING_AGREED) ?: false
    }

    override suspend fun saveMarketingAgreed(isAgreed: Boolean) {
        datastore.saveBoolean(KEY_MARKETING_AGREED, isAgreed)
    }

    override suspend fun getNickname(): String? {
        return datastore.getString(KEY_NICKNAME)

    }

    override suspend fun saveNickname(nickname: String) {
        datastore.saveString(KEY_NICKNAME, nickname)
    }

    override suspend fun getLoginSnsType(): SnsType? {
        return SnsType.valueOf(datastore.getString(KEY_LOGIN_SNS_TYPE) ?: return null)
    }

    override suspend fun saveLoginSnsType(type: SnsType) {
        datastore.saveString(KEY_LOGIN_SNS_TYPE, type.toString())
    }

    override suspend fun getVinylaToken(): VinylaToken? {
        return VinylaToken(datastore.getString(KEY_VINYLA_TOKEN) ?: return null)
    }

    override suspend fun saveVinylaToken(token: String) {
        datastore.saveString(KEY_VINYLA_TOKEN, token)
    }

    override suspend fun deleteVinylaToken() {
        datastore.deleteString(KEY_VINYLA_TOKEN)
    }

    companion object {
        private const val KEY_FCM_TOKEN = "KEY_FCM_TOKEN"
        private const val KEY_MARKETING_AGREED = "KEY_MARKETING_AGREED"
        private const val KEY_NICKNAME = "KEY_NICKNAME"
        private const val KEY_LOGIN_SNS_TYPE = "KEY_LOGIN_SNS_TYPE"
        private const val KEY_VINYLA_TOKEN = "KEY_VINYLA_TOKEN"
    }
}
