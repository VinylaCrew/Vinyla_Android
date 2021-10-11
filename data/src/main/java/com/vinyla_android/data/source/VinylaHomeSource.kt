package com.vinyla_android.data.source

import com.vinyla_android.domain.entity.VinylHome

/**
 * Created By Malibin
 * on 7월 26, 2021
 */

interface VinylaHomeSource {

    suspend fun getVinylHome(): VinylHome

    suspend fun saveVinylHome()
}
