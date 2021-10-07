package com.malibin.sns.auth.context

import android.app.Application
import android.content.Context
import com.malibin.sns.auth.dsl.SnsAuthConfig

/**
 * Created By Malibin
 * on 6월 19, 2021
 */

internal typealias SnsAuthConfigDeclaration = SnsAuthConfig.() -> Unit

fun Application.initSnsLogin(
    applicationContext: Context,
    configDeclaration: SnsAuthConfigDeclaration
) {
    configDeclaration(SnsAuthConfig())
}
