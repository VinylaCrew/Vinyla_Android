package com.vinyla_android.domain.entity.vinyl

/**
 * Created By Malibin
 * on 7월 28, 2021
 */

@JvmInline
value class Vinyls private constructor(
    private val vinyls: List<Vinyl>
) {
    fun get(): List<Vinyl> = this.vinyls

    operator fun plus(other: Vinyls): Vinyls = Vinyls(this.vinyls + other.vinyls)

    operator fun plus(vinylList: List<Vinyl>): Vinyls = Vinyls(this.vinyls + vinylList)

    operator fun minus(other: Vinyls): Vinyls = Vinyls(this.vinyls - other.vinyls)

    operator fun minus(vinylList: List<Vinyl>): Vinyls = Vinyls(this.vinyls - vinylList)

    override fun toString(): String {
        return vinyls.joinToString(
            prefix = "[",
            postfix = "]",
            transform = { "Vinyl(${it.title})" })
    }

    companion object {
        fun from(vinyls: List<Vinyl>): Vinyls = Vinyls(vinyls.toList())
    }
}
