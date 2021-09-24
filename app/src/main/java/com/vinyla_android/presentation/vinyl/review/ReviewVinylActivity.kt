package com.vinyla_android.presentation.vinyl.review

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vinyla_android.databinding.ActivityReviewVinylBinding

class ReviewVinylActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityReviewVinylBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
