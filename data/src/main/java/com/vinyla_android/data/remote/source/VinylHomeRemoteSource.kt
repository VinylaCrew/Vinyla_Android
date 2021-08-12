package com.vinyla_android.data.remote.source

import com.vinyla_android.data.remote.service.VinylaService
import com.vinyla_android.data.source.VinylHomeSource
import com.vinyla_android.domain.entity.VinylHome
import javax.inject.Inject

/**
 * Created By Malibin
 * on 7월 26, 2021
 */

class VinylHomeRemoteSource @Inject constructor(
    private val vinylaService: VinylaService,
) : VinylHomeSource {

    override suspend fun getVinylHome(): VinylHome {
        return vinylaService.getHomeInfo().data.toVinylHome()
    }

    override suspend fun saveVinylHome() {
        throw UnsupportedOperationException("remote saveVinylHome cannot be called")
    }
}
