package com.vinyla_android.fake.remote.repository

import com.vinyla_android.data.source.VinylaHomeSource
import com.vinyla_android.domain.entity.VinylHome
import javax.inject.Inject

/**
 * Created By Malibin
 * on 7월 26, 2021
 */

internal class FakeVinylaHomeSource @Inject constructor() : VinylaHomeSource {

    override suspend fun getVinylHome(): VinylHome {
        throw UnsupportedOperationException("stub!")
    }

    override suspend fun saveVinylHome() {
        throw UnsupportedOperationException("stub!")
    }
}
