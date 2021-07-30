package com.vinyla_android.presentation.vinyl.box

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vinyla_android.databinding.ActivityVinylBoxBinding

class VinylBoxActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityVinylBoxBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
