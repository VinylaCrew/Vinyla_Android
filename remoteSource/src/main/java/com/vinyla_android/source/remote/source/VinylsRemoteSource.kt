package com.vinyla_android.source.remote.source

import com.vinyla_android.source.remote.service.VinylaService
import com.vinyla_android.data.source.VinylsSource
import com.vinyla_android.domain.entity.SimpleVinyl
import com.vinyla_android.domain.entity.Vinyl
import javax.inject.Inject

/**
 * Created By Malibin
 * on 9월 03, 2021
 */

internal class VinylsRemoteSource @Inject constructor(
    private val vinylaService: VinylaService,
) : VinylsSource {

    override suspend fun getVinylOf(vinylId: Int): Vinyl? {
        val response = vinylaService.getVinyl(vinylId)
        return response.body()?.toVinyl()
        // response 관련 리팩터링 해야함 우선은 간단하게 처리
        // interceptor에서 가공 후 가져오는 작업도 만들어야함
    }

    override suspend fun searchVinyls(query: String): List<SimpleVinyl> {
        return vinylaService.searchVinyls(query)
            .body().orEmpty()
            .map { it.toSimpleVinyl() }
    }
}
