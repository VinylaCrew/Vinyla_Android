package com.vinyla_android.data.fake.repository

import com.vinyla_android.data.fake.STUB_VINYLS
import com.vinyla_android.domain.entity.vinyl.SimpleVinyl
import com.vinyla_android.domain.entity.vinyl.Vinyl
import com.vinyla_android.domain.entity.vinyl.Vinyls
import com.vinyla_android.domain.repository.VinylsRepository
import java.util.*
import javax.inject.Inject

internal class FakeVinylsRepository @Inject constructor() : VinylsRepository {

    private val inMemoryVinyls: MutableList<Vinyl> = STUB_VINYLS.toMutableList()

    override suspend fun getVinylOf(vinylId: Int): Vinyl? {
        return inMemoryVinyls.find { it.id == vinylId }
    }

    override suspend fun getCollectedVinyls(): Vinyls {
        return inMemoryVinyls.filter { it.isCollected }
            .sortedByDescending { it.collectedDate }
            .let { Vinyls.from(it) }
    }

    override suspend fun searchVinyls(query: String): List<SimpleVinyl> {
        return inMemoryVinyls.map { it.toSimpleVinyl() }
    }

    override suspend fun collectVinyl(
        vinyl: Vinyl,
        starScore: Float,
        comment: String
    ): Result<Unit> {
        val searchedVinyl = findVinylForce(vinyl.id)
        val updatedVinyl = searchedVinyl.copy(isCollected = true, collectedDate = Date())
        replaceInMemoryVinyl(searchedVinyl, updatedVinyl)
        return Result.success(Unit)
    }

    override suspend fun cancelCollectVinyl(vinylId: Int): Result<Unit> {
        val vinyl = findVinylForce(vinylId)
        val updatedVinyl = vinyl.copy(isCollected = false, collectedDate = null)
        replaceInMemoryVinyl(vinyl, updatedVinyl)
        return Result.success(Unit)
    }

    override suspend fun getRepresentativeVinyl(): Vinyl? {
        return inMemoryVinyls.find { it.isRepresentative }
    }

    override suspend fun saveRepresentativeVinyl(vinylId: Int): Result<Unit> {
        val vinyl = findVinylForce(vinylId)
        val updatedVinyl = vinyl.copy(isRepresentative = true)
        replaceInMemoryVinyl(vinyl, updatedVinyl)
        return Result.success(Unit)
    }

    override suspend fun removeRepresentativeVinyl(): Result<Unit> {
        val vinyl = inMemoryVinyls.find { it.isRepresentative }
            ?: error("cannot find representative Vinyl. maybe you don't save representative vinyl yet")
        val updatedVinyl = vinyl.copy(isRepresentative = false)
        replaceInMemoryVinyl(vinyl, updatedVinyl)
        return Result.success(Unit)
    }

    private suspend fun findVinylForce(vinylId: Int): Vinyl =
        getVinylOf(vinylId) ?: error("cannot find vinyl of id : $vinylId")

    private fun replaceInMemoryVinyl(old: Vinyl, new: Vinyl) {
        val oldIndex = inMemoryVinyls.indexOf(old)
        inMemoryVinyls.remove(old)
        inMemoryVinyls.add(oldIndex, new)
    }
}
