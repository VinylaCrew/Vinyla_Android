package com.vinyla_android.source.remote.response

import com.vinyla_android.domain.entity.VinylHome
import com.vinyla_android.domain.entity.VinylLevel

/**
 * Created By Malibin
 * on 7월 02, 2021
 */

data class HomeResponse(
    val myVinyl: VinylResponse,
    val recentVinyls: List<VinylResponse>,
    val nickname: String,
    val profileUrl: String?,
    val rank: LevelResponse,
    val vinylNum: Int,
    val myGenre: List<String>,
) {
    fun toVinylHome(): VinylHome = VinylHome(
        mainVinyl = myVinyl.toVinyl(),
        recentCollectedVinyls = recentVinyls.map { it.toVinyl() },
        nickName = nickname,
        vinylLevel = VinylLevel.find(rank.rankIdx),
        collectedCount = vinylNum,
        myGenre = myGenre.getOrNull(0).orEmpty(),
    )
}
