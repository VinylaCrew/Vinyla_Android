package com.vinyla_android.presentation.vinyl.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vinyla_android.databinding.ActivityVinylDetailsBinding

class VinylDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityVinylDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}
