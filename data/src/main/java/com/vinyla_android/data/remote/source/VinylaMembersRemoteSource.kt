package com.vinyla_android.data.remote.source

import com.vinyla_android.data.remote.service.VinylaService
import com.vinyla_android.data.remote.service.requestbody.CheckNicknameParams
import com.vinyla_android.data.source.VinylaMembersSource
import com.vinyla_android.domain.member.NicknameStatus
import javax.inject.Inject

/**
 * Created By Malibin
 * on 10월 09, 2021
 */

internal class VinylaMembersRemoteSource @Inject constructor(
    private val vinylaService: VinylaService,
) : VinylaMembersSource {

    override suspend fun checkNickname(nickname: String): NicknameStatus {
        val response = vinylaService.checkNickname(CheckNicknameParams(nickname))
        if (response.isSuccessful) return NicknameStatus.AVAILABLE
        return NicknameStatus.DUPLICATED // errorbody일 때 응답 읽어서 처리 따로해야됨 ㅠ
        // 우선 시간 없으니까 여긴 좀 나중에 다듬자 ㅠㅠ..
    }
}
