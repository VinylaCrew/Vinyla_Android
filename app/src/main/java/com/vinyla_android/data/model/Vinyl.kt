package com.vinyla_android.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Vinyl(
    @PrimaryKey
    val id: Long,
    val name: String,
    val artiestName: String,
    val genre: String,

    val imageUrl: String?,
    val thumbnailUrl: String?,

    var isCollected: Boolean = false,
    var collectedDate: Date? = null
)
