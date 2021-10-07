package com.malibin.sns.auth

import android.util.Log
import com.android.installreferrer.BuildConfig

fun printLog(message: String?) {
    if (BuildConfig.DEBUG) {
        Log.d("MalibinDebugSNS", message.toString())
    }
}
