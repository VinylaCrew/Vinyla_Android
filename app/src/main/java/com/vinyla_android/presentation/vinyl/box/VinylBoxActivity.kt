package com.vinyla_android.presentation.vinyl.box

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.vinyla_android.databinding.ActivityVinylBoxBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VinylBoxActivity : AppCompatActivity() {

    private val vinylBoxViewModel: VinylBoxViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityVinylBoxBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val vinylBoxPagesAdapter = VinylBoxPagesAdapter()
        binding.pagerCollections.adapter = vinylBoxPagesAdapter
//        binding.lifecycleOwner = this

        vinylBoxViewModel.pagedCollectedVinyls.observe(this) {
            vinylBoxPagesAdapter.submitList(it)
        }
        vinylBoxViewModel.loadCollectedVinyls()
    }
}
