package com.vinyla_android.presentation.mypage

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.vinyla_android.databinding.ActivityMyPageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageActivity : AppCompatActivity() {

    private val myPageViewModel: MyPageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMyPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.viewModel = myPageViewModel
        binding.buttonBack.setOnClickListener { finish() }
        binding.buttonLeave.setOnClickListener { deployActivity(LeaveActivity::class.java) }
        binding.buttonTermsAndConditionOfService.setOnClickListener {
            deployActivity(TermsOfServiceActivity::class.java)
        }
    }

    private fun <T> deployActivity(activityClass: Class<T>) {
        Intent(this, activityClass)
            .also { startActivity(it) }
    }
}
