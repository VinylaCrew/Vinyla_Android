package com.vinyla_android.presentation

import android.app.Application
import com.google.android.gms.ads.MobileAds
import com.kakao.sdk.common.KakaoSdk
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.vinyla_android.R
import dagger.hilt.android.HiltAndroidApp
import okhttp3.internal.platform.android.AndroidLog

/**
 * Created By Malibin
 * on 3월 27, 2021
 */

@HiltAndroidApp
class VinylaApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Logger.addLogAdapter(
            AndroidLogAdapter(
                PrettyFormatStrategy.newBuilder()
                    .methodCount(0)
                    .tag("MalibinDebug")
                    .build()
            )
        )
        MobileAds.initialize(this)
//        KakaoSdk.init(this, getString(R.string.kakaoNativeAppKey))
    }
}
