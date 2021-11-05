package com.vinyla_android.presentation.vinyl.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.vinyla_android.databinding.ActivityVinylDetailsBinding
import com.vinyla_android.presentation.vinyl.review.ReviewVinylActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VinylDetailsActivity : AppCompatActivity() {

    private val vinylDetailsViewModel: VinylDetailsViewModel by viewModels()
    private val vinylTracksAdapter: VinylTracksAdapter by lazy { VinylTracksAdapter() }
    private val reviewVinylActivityLauncher = createReviewVinylActivityLauncher()

    private fun createReviewVinylActivityLauncher(): ActivityResultLauncher<Intent> {
        val contract = ActivityResultContracts.StartActivityForResult()
        return registerForActivityResult(contract) { handleReviewVinylActivityResult(it) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityVinylDetailsBinding.inflate(layoutInflater)
        initView(binding)
        vinylDetailsViewModel.loadVinylDetails(getVinylId())
        vinylDetailsViewModel.vinyl.observe(this) { vinylTracksAdapter.submitList(it.trackList) }
    }

    private fun getVinylId(): Int {
        return intent.getIntExtra(KEY_VINYL_ID, KEY_DEFAULT_VALUE)
            .takeIf { it != KEY_DEFAULT_VALUE }
            ?: throw IllegalArgumentException("Vinyl Key must be send")
    }

    private fun initView(binding: ActivityVinylDetailsBinding) {
        setContentView(binding.root)
        binding.viewModel = vinylDetailsViewModel
        binding.lifecycleOwner = this
        binding.listVinylTracks.adapter = vinylTracksAdapter
        binding.buttonAddCollection.setOnClickListener { deployReviewVinylActivity() }
        binding.buttonBack.setOnClickListener { finish() }
        binding.buttonShareInstagram.setOnClickListener { shareInstagram() }
    }

    private fun deployReviewVinylActivity() {
        val intent = Intent(this, ReviewVinylActivity::class.java)
        intent.putExtra(ReviewVinylActivity.KEY_VINYL_ID, getVinylId())
        reviewVinylActivityLauncher.launch(intent)
    }

    private fun handleReviewVinylActivityResult(result: ActivityResult) {
        if (result.resultCode != Activity.RESULT_OK) return
        vinylDetailsViewModel.loadVinylDetails(getVinylId())
    }

    private fun shareInstagram() {

    }

    companion object {
        const val KEY_VINYL_ID = "KEY_VINYL_ID"
        const val KEY_DEFAULT_VALUE = Int.MIN_VALUE
    }
}
