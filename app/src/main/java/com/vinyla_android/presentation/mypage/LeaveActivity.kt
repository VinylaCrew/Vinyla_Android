package com.vinyla_android.presentation.mypage

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.vinyla_android.databinding.ActivityLeaveBinding
import com.vinyla_android.presentation.SplashActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LeaveActivity : AppCompatActivity() {

    private val leaveViewModel: LeaveViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityLeaveBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewModel = leaveViewModel
        binding.lifecycleOwner = this

        leaveViewModel.isLeaveSuccess.observe(this) { onLeaveSuccess() }
    }

    private fun onLeaveSuccess() {
        Intent(this, SplashActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            .also { startActivity(it) }
    }
}
