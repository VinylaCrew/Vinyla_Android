package com.vinyla_android.presentation.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vinyla_android.databinding.ActivityLeaveBinding

class LeaveActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityLeaveBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
