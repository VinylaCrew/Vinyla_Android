package com.vinyla_android.data.local.source

import com.vinyla_android.data.source.VinylaMembersSource
import com.vinyla_android.domain.member.NicknameStatus
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
}
