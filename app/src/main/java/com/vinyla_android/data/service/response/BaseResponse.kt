package com.vinyla_android.data.service.response

/**
 * Created By Malibin
 * on 6월 29, 2021
 */

data class BaseResponse<T>(
    val statusCode: Int,
    val responseMessage: String,
    val data: T
)
