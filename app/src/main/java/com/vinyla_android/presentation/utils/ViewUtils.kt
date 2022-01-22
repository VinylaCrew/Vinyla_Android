package com.vinyla_android.presentation.utils

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.orhanobut.logger.Logger

/**
 * Created By Malibin
 * on 3월 28, 2020
 */

fun printLog(message: String?) = Logger.d(message.toString())

fun Context.showToast(@StringRes stringRes: Int) = showToast(ToastMessage.ResourceId(stringRes))

fun Context.showToast(message: String) = showToast(ToastMessage.StringValue(message))

fun Context.showToast(toastMessage: ToastMessage) = when (toastMessage) {
    is ToastMessage.ResourceId ->
        Toast.makeText(this, toastMessage.message, Toast.LENGTH_SHORT).show()
    is ToastMessage.StringValue ->
        Toast.makeText(this, toastMessage.message, Toast.LENGTH_SHORT).show()
}

fun AdView.loadAd() {
    this.loadAd(AdRequest.Builder().build())
}
