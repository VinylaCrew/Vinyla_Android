package com.malibin.test.utils.lifecycle

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/**
 * Created By Malibin
 * on 2월 24, 2020
 */

fun <T> LiveData<T>.takeValue(): T? {
    var result: T? = null
    val countDownLatch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(t: T) {
            result = t
            countDownLatch.countDown()
            removeObserver(this)
        }
    }
    this.observeForever(observer)
    countDownLatch.await(2000, TimeUnit.MILLISECONDS)
    return result
}
