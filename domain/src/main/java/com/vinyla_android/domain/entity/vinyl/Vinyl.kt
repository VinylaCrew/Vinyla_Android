package com.vinyla_android.domain.entity.vinyl

import java.util.*

data class Vinyl(
    val id: Int,
    val title: String,
    val artistName: String,
    val genre: String,
    val trackList: List<VinylTrack>,
    val releaseYear: Int,
    val starScore: Float,
    val reviewCount: Int,

    val imageUrl: String?,
    val thumbnailUrl: String?,

    val isRepresentative: Boolean = false,
    val isCollected: Boolean = false,
    val collectedDate: Date? = null,
) {
    fun toSimpleVinyl(): SimpleVinyl = SimpleVinyl(
        id = id,
        thumbnailUrl = thumbnailUrl.orEmpty(),
        title = title,
        artistName = artistName,
    )
}
