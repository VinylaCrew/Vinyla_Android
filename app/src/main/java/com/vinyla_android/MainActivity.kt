package com.vinyla_android

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdView
import com.google.zxing.integration.android.IntentIntegrator
import com.vinyla_android.presentation.utils.loadAd

class MainActivity : AppCompatActivity() {
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
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        Toast.makeText(
            this,
            """
            ${result.barcodeImagePath}
            ${result.contents}
            ${result.errorCorrectionLevel}
            ${result.formatName}
            ${result.orientation}
            ${result.originalIntent}
            ${result.rawBytes}
        """.trimIndent(),
            Toast.LENGTH_LONG
        ).show()
    }
}
