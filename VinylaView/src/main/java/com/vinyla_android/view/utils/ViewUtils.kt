package com.vinyla_android.view.utils

import android.content.res.Resources

/**
 * Created By Malibin
 * on 5월 08, 2021
 */

fun Float.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

fun Int.toDp(): Float = this / Resources.getSystem().displayMetrics.density
