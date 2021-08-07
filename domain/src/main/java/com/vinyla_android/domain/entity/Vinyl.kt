package com.vinyla_android.domain.entity

import java.util.*

data class Vinyl(
    val id: Long,
    val name: String,
    val artiestName: String,
    val genre: String,

    val imageUrl: String?,
    val thumbnailUrl: String?,

    var isCollected: Boolean = false,
    var collectedDate: Date? = null
)
