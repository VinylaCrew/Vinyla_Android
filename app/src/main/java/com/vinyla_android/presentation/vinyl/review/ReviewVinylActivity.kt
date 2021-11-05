package com.vinyla_android.presentation.vinyl.review

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.vinyla_android.databinding.ActivityReviewVinylBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReviewVinylActivity : AppCompatActivity() {

    private val reviewVinylViewModel: ReviewVinylViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityReviewVinylBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewModel = reviewVinylViewModel
        binding.lifecycleOwner = this
        binding.buttonBack.setOnClickListener { finish() }

        reviewVinylViewModel.loadVinyl(getVinylId())
    }

    private fun getVinylId(): Int {
        return intent.getIntExtra(KEY_VINYL_ID, KEY_DEFAULT_VALUE)
            .takeIf { it != KEY_DEFAULT_VALUE }
            ?: throw IllegalArgumentException("Vinyl Key must be send")
    }

    companion object {
        const val KEY_VINYL_ID = "KEY_VINYL_ID"
        const val KEY_DEFAULT_VALUE = Int.MIN_VALUE
    }
}
