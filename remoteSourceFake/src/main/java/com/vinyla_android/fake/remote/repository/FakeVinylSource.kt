package com.vinyla_android.fake.remote.repository

import com.vinyla_android.data.source.VinylsSource
import com.vinyla_android.domain.entity.SimpleVinyl
import com.vinyla_android.domain.entity.Vinyl
import com.vinyla_android.fake.remote.STUB_VINYLS
import java.util.*
import javax.inject.Inject

internal class FakeVinylSource @Inject constructor() : VinylsSource {

    override suspend fun getVinylOf(vinylId: Int): Vinyl? {
        return STUB_VINYLS.find { it.id == vinylId }
    }

    override suspend fun searchVinyls(query: String): List<SimpleVinyl> {
        return STUB_VINYLS.map { it.toSimpleVinyl() }
    }
}
