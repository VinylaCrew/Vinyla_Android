package com.vinyla_android.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vinyla_android.databinding.ActivityTempHomeBinding

class TempHomeActivity : AppCompatActivity() {

    private var binding: ActivityTempHomeBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTempHomeBinding.inflate(layoutInflater)
            .also { setContentView(it.root) }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
