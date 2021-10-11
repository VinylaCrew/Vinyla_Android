package com.vinyla_android.presentation.vinyl.search

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.vinyla_android.databinding.ActivitySearchVinylBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchVinylActivity : AppCompatActivity() {

    private val searchedVinylsAdapter = SearchedVinylsAdapter()
    private val searchVinylViewModel: SearchVinylViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivitySearchVinylBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.listSearchedVinyls.adapter = searchedVinylsAdapter
        binding.buttonBack.setOnClickListener { finish() }
        binding.viewModel = searchVinylViewModel

        searchVinylViewModel.searchedVinyls.observe(this) {
            searchedVinylsAdapter.submitList(it)
        }
    }
}
