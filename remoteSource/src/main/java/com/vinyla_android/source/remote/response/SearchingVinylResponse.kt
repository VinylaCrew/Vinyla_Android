package com.vinyla_android.source.remote.response

import com.google.gson.annotations.SerializedName
import com.vinyla_android.domain.entity.SimpleVinyl

/**
 * Created By Malibin
 * on 9월 02, 2021
 */

data class SearchingVinylResponse(
    @SerializedName("id")
    val vinylId: Int,
    @SerializedName("thumb")
    val thumbnailUrl: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("artist")
    val artistName: String,
) {
    fun toSimpleVinyl(): SimpleVinyl = SimpleVinyl(
        id = vinylId,
        thumbnailUrl = thumbnailUrl,
        title = title,
        artiestName = artistName,
    )
}
