package com.vinyla_android.presentation

import android.app.Application
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.HiltAndroidApp

/**
 * Created By Malibin
 * on 3월 27, 2021
 */

@HiltAndroidApp
class VinylaApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        MobileAds.initialize(this)
    }
}
