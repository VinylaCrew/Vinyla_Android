package com.vinyla_android.data.source.remote

import com.vinyla_android.data.model.VinylHome
import com.vinyla_android.data.service.VinylaService
import com.vinyla_android.data.source.VinylHomeSource
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
