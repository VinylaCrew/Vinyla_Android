package com.vinyla_android.data.remote.response

data class CollectedVinylsResponse(
    val userIdx: Int,
    val myVinyls: List<MyVinylResponse>
) {
    data class MyVinylResponse(
        val vinylIdx: Int,
        val title: String,
        val imageUrl: String,
        val artist: String,
        val id: Int,
    )
}
