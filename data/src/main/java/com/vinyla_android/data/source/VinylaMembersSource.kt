package com.vinyla_android.data.source

import com.vinyla_android.domain.member.NicknameStatus

/**
 * Created By Malibin
 * on 10월 09, 2021
 */

internal interface VinylaMembersSource {

    suspend fun checkNickname(nickname: String): NicknameStatus
}
