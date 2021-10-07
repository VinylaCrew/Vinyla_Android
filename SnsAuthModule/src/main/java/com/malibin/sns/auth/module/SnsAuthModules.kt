package com.malibin.sns.auth.module

import com.malibin.sns.auth.model.SnsType

/**
 * Created By Malibin
 * on 6월 19, 2021
 */

class SnsAuthModules(
    private val snsAuthModuleMap: Map<SnsType, SnsAuthModule>
) {
    operator fun get(snsType: SnsType): SnsAuthModule {
        return snsAuthModuleMap[snsType]
            ?: error("$snsType Auth is not initialized. Please initialize $snsType Auth in \"initSnsLogin\" block on Application")
    }
}
