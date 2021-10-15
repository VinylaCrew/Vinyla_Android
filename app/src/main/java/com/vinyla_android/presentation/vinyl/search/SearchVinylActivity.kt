package com.vinyla_android.presentation.vinyl.search

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.vinyla_android.databinding.ActivitySearchVinylBinding
import com.vinyla_android.presentation.vinyl.detail.VinylDetailsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchVinylActivity : AppCompatActivity() {

    private val searchedVinylsAdapter = SearchedVinylsAdapter()
    private val searchVinylViewModel: SearchVinylViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivitySearchVinylBinding.inflate(layoutInflater)
        initView(binding)

        searchedVinylsAdapter.setOnVinylClickListener { deployVinylDetailsActivity(it.id) }
        searchVinylViewModel.searchedVinyls.observe(this) { searchedVinylsAdapter.submitList(it) }
    }

    private fun initView(binding: ActivitySearchVinylBinding) {
        setContentView(binding.root)
        binding.viewModel = searchVinylViewModel
        binding.lifecycleOwner = this
        binding.listSearchedVinyls.adapter = searchedVinylsAdapter
        binding.buttonBack.setOnClickListener { finish() }
        binding.textSearchQuery.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) searchVinylViewModel.searchVinyls()
            true
        }
    }

    private fun deployVinylDetailsActivity(vinylId: Int) {
        Intent(this, VinylDetailsActivity::class.java)
            .putExtra(VinylDetailsActivity.KEY_VINYL_ID, vinylId)
            .also { startActivity(it) }
    }
}
