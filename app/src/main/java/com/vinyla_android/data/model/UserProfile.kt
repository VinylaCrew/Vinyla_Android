package com.vinyla_android.data.model

import android.os.Parcelable
import com.vinyla_android.presentation.login.auth.SnsAuth
import kotlinx.android.parcel.Parcelize

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

// sns login module 쪽 도메인으로 가져가야할듯
