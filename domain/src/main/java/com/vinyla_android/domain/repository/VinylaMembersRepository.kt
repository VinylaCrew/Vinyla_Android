package com.vinyla_android.domain.repository

import com.vinyla_android.domain.member.NicknameStatus

/**
 * Created By Malibin
 * on 10월 09, 2021
 */

interface VinylaMembersRepository {

    suspend fun checkNickname(nickname: String): NicknameStatus
}
