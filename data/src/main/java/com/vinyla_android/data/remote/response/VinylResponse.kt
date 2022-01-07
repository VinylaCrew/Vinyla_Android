package com.vinyla_android.data.remote.response

import com.vinyla_android.domain.entity.vinyl.Vinyl

/**
 * Created By Malibin
 * on 7월 02, 2021
 */

internal data class VinylResponse(
    val vinylIdx: Int,
    val title: String,
    val imageUrl: String,
    val artist: String,
) {
    fun toVinyl(): Vinyl = Vinyl(
        id = vinylIdx,
        title = title,
        artistName = artist,
        genre = "",
        trackList = emptyList(),
        imageUrl = imageUrl,
        thumbnailUrl = imageUrl,
        releaseYear = 0,
        starScore = 0.0f,
        reviewCount = 0,
    )
}
