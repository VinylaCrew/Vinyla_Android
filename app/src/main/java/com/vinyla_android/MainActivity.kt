package com.vinyla_android

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facebook.Profile
import com.google.android.gms.ads.AdView
import com.google.zxing.integration.android.IntentIntegrator
import com.vinyla_android.presentation.login.auth.SnsAuth
import com.vinyla_android.presentation.login.auth.SnsAuthManager
import com.vinyla_android.presentation.utils.loadAd
import com.vinyla_android.presentation.utils.printLog

class MainActivity : AppCompatActivity() {

    private val snsAuthManager: SnsAuthManager by lazy { SnsAuthManager(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<AdView>(R.id.banner).loadAd()
        findViewById<Button>(R.id.button).setOnClickListener {
            IntentIntegrator(this)
                .setOrientationLocked(false)
                .setBeepEnabled(false)
                .initiateScan()
        }

        findViewById<Button>(R.id.button_kakao).setOnClickListener { proceedKakaoLogin() }
        findViewById<Button>(R.id.button_facebook).setOnClickListener { proceedFacebookLogin() }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        printLog(Profile.getCurrentProfile()?.toString())


        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        Toast.makeText(
            this,
            """
            ${result?.barcodeImagePath}
            ${result?.contents}
            ${result?.errorCorrectionLevel}
            ${result?.formatName}
            ${result?.orientation}
            ${result?.originalIntent}
            ${result?.rawBytes}
        """.trimIndent(),
            Toast.LENGTH_LONG
        ).show()
    }

    private fun proceedKakaoLogin() {
        snsAuthManager.login(SnsAuth.Type.KAKAO) { printLog(it.toString()) }
    }

    private fun proceedFacebookLogin() {
        snsAuthManager.login(SnsAuth.Type.FACEBOOK) { printLog(it.toString()) }
    }

    override fun onDestroy() {
        super.onDestroy()
        snsAuthManager.quit(SnsAuth.Type.KAKAO)
        snsAuthManager.logout(SnsAuth.Type.FACEBOOK)
    }
}
