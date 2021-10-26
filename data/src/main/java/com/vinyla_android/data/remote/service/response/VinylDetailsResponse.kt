package com.vinyla_android.data.remote.service.response

import com.google.gson.annotations.SerializedName
import com.vinyla_android.domain.entity.Vinyl

/**
 * Created By Malibin
 * on 9월 02, 2021
 */

data class VinylDetailsResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("artist")
    val artistName: String,
    @SerializedName("image")
    val imageUrl: String,
    @SerializedName("year")
    val year: Int,
    @SerializedName("rate")
    val rate: Float,
    @SerializedName("rateCount")
    val rateCount: Int,
    @SerializedName("genres")
    val genres: List<String>,
    @SerializedName("tracklist")
    val tracks: List<VinylTrackResponse>,
) {
    fun toVinyl(): Vinyl = Vinyl(
        id = id,
        name = title,
        artiestName = artistName,
        genre = genres[0], // 여기 변경해야함
        imageUrl = imageUrl,
        thumbnailUrl = imageUrl,
        releaseYear = year,
        starScore = rate,
        reviewCount = rateCount,
    )
}
