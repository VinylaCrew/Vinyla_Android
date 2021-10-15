package com.vinyla_android.presentation.vinyl.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.vinyla_android.databinding.ActivityVinylDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VinylDetailsActivity : AppCompatActivity() {

    private val vinylDetailsViewModel: VinylDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityVinylDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    companion object {
        const val KEY_VINYL_ID = "KEY_VINYL_ID"
    }
}
