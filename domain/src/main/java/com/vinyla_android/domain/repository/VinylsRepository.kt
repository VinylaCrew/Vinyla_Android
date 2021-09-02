package com.vinyla_android.domain.repository

import com.vinyla_android.domain.entity.SimpleVinyl
import com.vinyla_android.domain.entity.Vinyl

/**
 * Created By Malibin
 * on 9월 02, 2021
 */

interface VinylsRepository {

    suspend fun getVinylOf(vinylId: Int): Vinyl?

    suspend fun searchVinyls(query: String): List<SimpleVinyl>
}
