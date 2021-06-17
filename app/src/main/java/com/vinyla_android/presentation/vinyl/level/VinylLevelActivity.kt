package com.vinyla_android.presentation.vinyl.level

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vinyla_android.databinding.ActivityVinylLevelBinding

class VinylLevelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityVinylLevelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val vinylLevelAdapter = VinylLevelAdapter().apply { submitList(VinylLevel.all()) }
        binding.listVinylLevel.adapter = vinylLevelAdapter
    }
}
