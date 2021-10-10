package com.vinyla_android.domain.member

/**
 * Created By Malibin
 * on 10월 10, 2021
 */

data class SignUpInfo(
    val nickname: String,
    val instagramId: String,
    val profileImageUrl: String,
    val snsType: SnsType,
    val marketingAgreed: Boolean,
)
