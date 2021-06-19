package com.malibin.sns.auth

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created By Malibin
 * on 4월 12, 2021
 */

@Parcelize
data class UserProfile(
    val nickname: String,
    val profileUrl: String,
    val authType: SnsAuth.Type,
) : Parcelable
