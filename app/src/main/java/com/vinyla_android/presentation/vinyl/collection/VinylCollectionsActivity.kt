package com.vinyla_android.presentation.vinyl.collection

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vinyla_android.databinding.ActivityVinylCollectionsBinding

class VinylCollectionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityVinylCollectionsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}