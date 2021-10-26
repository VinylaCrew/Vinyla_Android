package com.vinyla_android.data.remote.service.response

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
        id = vinylIdx,
        name = title,
        artiestName = artist,
        genre = "",
        imageUrl = imageUrl,
        thumbnailUrl = imageUrl,
        releaseYear = 0,
        starScore = 0.0f,
        reviewCount = 0,
    )
}
