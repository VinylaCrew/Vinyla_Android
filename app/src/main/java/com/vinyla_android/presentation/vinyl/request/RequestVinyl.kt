package com.vinyla_android.presentation.vinyl.request

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vinyla_android.databinding.ActivityRequestVinylBinding

class RequestVinyl : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityRequestVinylBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
