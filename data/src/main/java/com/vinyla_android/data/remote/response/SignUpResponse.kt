package com.vinyla_android.data.remote.response

/**
 * Created By Malibin
 * on 10월 10, 2021
 */

internal data class SignUpResponse(
    val token: String,
    val nickname:String,
    val subscribeAgreed: Int, // 1 - true // 0 - false
)
