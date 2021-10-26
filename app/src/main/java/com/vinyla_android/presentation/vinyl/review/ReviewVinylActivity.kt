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

        reviewVinylViewModel.loadVinyl(0)
    }
}
