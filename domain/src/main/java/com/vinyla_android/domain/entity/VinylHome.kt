package com.vinyla_android.domain.entity

/**
 * Created By Malibin
 * on 7월 26, 2021
 */

data class VinylHome(
    val mainVinyl: Vinyl,
    val recentCollectedVinyls: List<Vinyl>,
    val nickName: String,
    val vinylLevel: VinylLevel,
    val collectedCount: Int,
    val myGenre: String
) {
    val recentVinylOne: Vinyl?
        get() = recentCollectedVinyls.getOrNull(0)
    val recentVinylTwo: Vinyl?
        get() = recentCollectedVinyls.getOrNull(1)
    val recentVinylThree: Vinyl?
        get() = recentCollectedVinyls.getOrNull(2)
    val recentVinylFour: Vinyl?
        get() = recentCollectedVinyls.getOrNull(3)
}
