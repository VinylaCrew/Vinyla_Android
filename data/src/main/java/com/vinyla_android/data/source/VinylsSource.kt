package com.vinyla_android.data.source

import com.vinyla_android.domain.entity.vinyl.SimpleVinyl
import com.vinyla_android.domain.entity.vinyl.Vinyl
import com.vinyla_android.domain.entity.vinyl.Vinyls

/**
 * Created By Malibin
 * on 9월 02, 2021
 */

interface VinylsSource {

    suspend fun getVinylOf(vinylId: Int): Vinyl?

    suspend fun getCollectedVinyls(): Vinyls

    suspend fun searchVinyls(query: String): List<SimpleVinyl>

    suspend fun collectVinyl(vinylId: Int, starScore: Float, comment: String): Result<Unit>

    suspend fun cancelCollectVinyl(vinylId: Int): Result<Unit>

    suspend fun getRepresentativeVinyl(): Vinyl?

    suspend fun saveRepresentativeVinyl(vinylId: Int): Result<Unit>

    suspend fun removeRepresentativeVinyl(): Result<Unit>
}
