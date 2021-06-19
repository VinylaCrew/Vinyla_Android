package com.malibin.sns.auth

/**
 * Created By Malibin
 * on 6월 19, 2021
 */

class SnsAuthHolder(
    private val snsAuthMap: Map<SnsType, SnsAuth>
) {
    fun find(type: SnsType): SnsAuth {
        return snsAuthMap[type]
            ?: error("$type Auth is not initialized. Please initialize $type Auth in \"initSnsLogin\" block on Application")
    }
}
