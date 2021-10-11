package com.vinyla_android.data.remote.service.response

/**
 * Created By Malibin
 * on 6월 29, 2021
 */

data class VinylaResponse<T>(
    val status: Int,
    val message: String,
    val data: T
)
