package com.vinyla_android.data.service.response

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
)
