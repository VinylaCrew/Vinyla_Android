package com.vinyla_android.source.remote.source

import com.vinyla_android.source.remote.service.VinylaService
import com.vinyla_android.data.source.VinylaHomeSource
import com.vinyla_android.domain.entity.vinyl.VinylHome
import javax.inject.Inject

/**
 * Created By Malibin
 * on 7월 26, 2021
 */

internal class VinylaHomeRemoteSource @Inject constructor(
    private val vinylaService: VinylaService,
) : VinylaHomeSource {

    override suspend fun getVinylHome(): VinylHome {
        val response = vinylaService.getHomeInfo()
        return response.body()?.toVinylHome() ?: error("server error")
    }
    // 흐음.... 이곳은 서버가 나와봐야 그때가서 설계가 가능할듯.......
    // 서버가 어디까지 나눠서 줄 지 정해지지 않으니 함부러 설계하기가 힘들다. 우선은 냅둬야할듯..
    // Source의 리턴값을 nullable 하게 정도로 수정해서 서버 응답이 없는 경우 local로 돌리게끔.
    // 또는 Result<T> 로 처리하자.

    override suspend fun saveVinylHome() {
        throw UnsupportedOperationException("remote saveVinylHome cannot be called")
    }
}
