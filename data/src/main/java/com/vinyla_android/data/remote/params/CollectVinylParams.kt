package com.vinyla_android.data.remote.params

data class CollectVinylParams(
    val id: Int,
    val title: String,
    val artist: String,
    val image: String,
    val year: Int,
    val genres: List<String>,
    val tracklist: List<String>,
    val rate: Int,
    val comment: String,
)
