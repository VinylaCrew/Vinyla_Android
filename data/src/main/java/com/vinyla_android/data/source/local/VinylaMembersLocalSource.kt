package com.vinyla_android.data.source.local

import com.vinyla_android.data.local.datastore.DataStoreManager
import com.vinyla_android.data.source.VinylaMembersSource
import com.vinyla_android.domain.entity.member.SignUpInfo
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

    override suspend fun signUp(signUpInfo: SignUpInfo): Result<Unit> {
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

    companion object {
        private const val KEY_FCM_TOKEN = "KEY_FCM_TOKEN"
    }
}
