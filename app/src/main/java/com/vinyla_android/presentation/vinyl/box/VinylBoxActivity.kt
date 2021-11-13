package com.vinyla_android.presentation.vinyl.box

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.vinyla_android.databinding.ActivityVinylBoxBinding
import com.vinyla_android.presentation.vinyl.detail.VinylDetailsActivity
import com.vinyla_android.presentation.vinyl.search.SearchVinylActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VinylBoxActivity : AppCompatActivity() {

    private val vinylBoxViewModel: VinylBoxViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityVinylBoxBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonAddVinyl.setOnClickListener { deploySearchVinylActivity() }

        val vinylBoxPagesAdapter = VinylBoxPagesAdapter().apply {
            setOnVinylClickListener { deployVinylDetailsActivity(it.id) }
        }
        binding.pagerCollections.adapter = vinylBoxPagesAdapter
        binding.lifecycleOwner = this

        vinylBoxViewModel.pagedCollectedVinyls.observe(this) {
            vinylBoxPagesAdapter.submitList(it)
        }
    }

    override fun onResume() {
        super.onResume()
        vinylBoxViewModel.loadCollectedVinyls()
    }

    private fun deploySearchVinylActivity() {
        val intent = Intent(this, SearchVinylActivity::class.java)
        startActivity(intent)
    }

    private fun deployVinylDetailsActivity(vinylId: Int) {
        val intent = Intent(this, VinylDetailsActivity::class.java)
        intent.putExtra(VinylDetailsActivity.KEY_VINYL_ID, vinylId)
        startActivity(intent)
    }
}
