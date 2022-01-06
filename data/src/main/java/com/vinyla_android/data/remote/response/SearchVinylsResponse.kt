package com.vinyla_android.data.remote.response

import com.google.gson.annotations.SerializedName
import com.vinyla_android.domain.entity.vinyl.SimpleVinyl

/**
 * Created By Malibin
 * on 9월 02, 2021
 */

data class SearchVinylsResponse(
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
        artistName = artistName,
    )
}
