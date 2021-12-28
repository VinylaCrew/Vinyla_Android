package com.vinyla_android.domain.repository

import com.vinyla_android.domain.entity.vinyl.VinylHome

/**
 * Created By Malibin
 * on 10월 11, 2021
 */

interface VinylaHomeRepository {

    suspend fun getVinylHome(): VinylHome

    suspend fun saveVinylHome()
}
