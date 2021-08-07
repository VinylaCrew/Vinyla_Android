package com.vinyla_android.data.service.response

import com.vinyla_android.domain.entity.Vinyl

/**
 * Created By Malibin
 * on 7월 02, 2021
 */

data class VinylResponse(
    val vinylIdx: Int,
    val title: String,
    val imageUrl: String,
    val artist: String,
) {
    fun toVinyl(): Vinyl = Vinyl(
        id = vinylIdx.toLong(),
        name = title,
        artiestName = artist,
        genre = "",
        imageUrl = imageUrl,
        thumbnailUrl = imageUrl,
    )
}
