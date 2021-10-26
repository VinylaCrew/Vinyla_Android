package com.vinyla_android.domain.entity

import java.util.*

data class Vinyl(
    val id: Int,
    val name: String,
    val artiestName: String,
    val genre: String,
    val releaseYear: Int,
    val starScore: Float,
    val reviewCount: Int,

    val imageUrl: String?,
    val thumbnailUrl: String?,

    var isCollected: Boolean = false,
    var collectedDate: Date? = null
)
