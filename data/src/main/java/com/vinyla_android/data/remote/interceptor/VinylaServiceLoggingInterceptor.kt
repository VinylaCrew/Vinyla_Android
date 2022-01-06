package com.vinyla_android.data.remote.interceptor

import okhttp3.logging.HttpLoggingInterceptor

/**
 * Created By Malibin
 * on 3월 28, 2021
 */

val HTTP_LOGGING_INTERCEPTOR: HttpLoggingInterceptor = HttpLoggingInterceptor {
//    try {
//        JSONObject(it)
//        Logger.t(TAG).json(it)
//    } catch (e: Exception) {
//        Logger.t(TAG).d(it)
//    }
}.apply { level = HttpLoggingInterceptor.Level.BODY }

private const val TAG = "MalibinHTTP"
