package com.vinyla_android.presentation.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vinyla_android.databinding.ActivityQuitBinding

class QuitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityQuitBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}