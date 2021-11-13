package com.vinyla_android.presentation.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vinyla_android.databinding.ActivityHomeBinding
import com.vinyla_android.presentation.vinyl.box.VinylBoxActivity
import com.vinyla_android.presentation.vinyl.level.VinylLevelActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonVinylLevel.setOnClickListener {
            val intent = Intent(this, VinylLevelActivity::class.java)
            startActivity(intent)
        }

        binding.buttonCollection.setOnClickListener {
            val intent = Intent(this, VinylBoxActivity::class.java)
            startActivity(intent)
        }
    }
}
