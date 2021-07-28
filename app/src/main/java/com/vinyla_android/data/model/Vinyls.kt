package com.vinyla_android.data.model

/**
 * Created By Malibin
 * on 7월 28, 2021
 */

data class Vinyls(
    private val vinyls: List<Vinyl>
) {
    fun get(): List<Vinyl> = this.vinyls

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Vinyls

        if (vinyls != other.vinyls) return false

        return true
    }

    override fun hashCode(): Int {
        return vinyls.hashCode()
    }
}
