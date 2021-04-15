package com.vinlya_android.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/**
 * Created By Malibin
 * on 4월 15, 2021
 */

fun <T> LiveData<T>.takeValue(): T? {
    var result: T? = null
    val countDownLatch = CountDownLatch(1)
    var observer = Observer<T> {}

    observer = Observer {
        result = it
        countDownLatch.countDown()
        this.removeObserver(observer)
    }
    this.observeForever(observer)
    countDownLatch.await(2000, TimeUnit.MILLISECONDS)
    return result
}
