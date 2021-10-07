package com.vinyla_android

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdView
import com.google.zxing.integration.android.IntentIntegrator
import com.malibin.sns.auth.SnsAuth
import com.malibin.sns.auth.model.SnsType
import com.vinyla_android.presentation.utils.loadAd
import com.vinyla_android.presentation.utils.printLog

class MainActivity : AppCompatActivity() {

    private val snsAuth: SnsAuth by lazy { SnsAuth.getInstance() }

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

        findViewById<Button>(R.id.button_google).setOnClickListener { proceedKakaoLogin() }
        findViewById<Button>(R.id.button_apple_test).setOnClickListener { proceedFacebookLogin() }
        findViewById<Button>(R.id.button_facebook_quit).setOnClickListener {
            snsAuth.unlink(SnsType.FACEBOOK)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        snsAuth.onActivityResult(requestCode, resultCode, data)

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
        snsAuth.login(SnsType.KAKAO, this) { printLog("kakao profile : $it") }
    }

    private fun proceedFacebookLogin() {
        snsAuth.login(SnsType.FACEBOOK, this) { printLog("facebook profile : $it") }
    }

    override fun onDestroy() {
        super.onDestroy()
        snsAuth.unlink(SnsType.KAKAO)
        snsAuth.logout(SnsType.FACEBOOK)
    }
}
