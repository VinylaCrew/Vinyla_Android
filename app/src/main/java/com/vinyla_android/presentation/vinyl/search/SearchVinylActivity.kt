package com.vinyla_android.presentation.vinyl.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vinyla_android.databinding.ActivitySearchVinylBinding

class SearchVinylActivity : AppCompatActivity() {

    private val searchedVinylsAdapter = SearchedVinylsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivitySearchVinylBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.listSearchedVinyls.adapter = searchedVinylsAdapter
        binding.buttonBack.setOnClickListener { finish() }
    }
}
