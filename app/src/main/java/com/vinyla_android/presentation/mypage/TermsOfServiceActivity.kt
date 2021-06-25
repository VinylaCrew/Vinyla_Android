package com.vinyla_android.presentation.mypage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vinyla_android.R
import com.vinyla_android.databinding.ActivityTermsOfServiceBinding

class TermsOfServiceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityTermsOfServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonTermsAndConditionsOfService.isSelected = true

        binding.buttonTermsAndConditionsOfService.setOnClickListener {
            binding.buttonPrivacyPolicy.isSelected = false
            binding.textContent.setText(R.string.terms_and_conditions_of_service_content)
            binding.buttonTermsAndConditionsOfService.isSelected = true
        }

        binding.buttonPrivacyPolicy.setOnClickListener {
            binding.buttonTermsAndConditionsOfService.isSelected = false
            binding.textContent.setText(R.string.privacy_policy_content)
            binding.buttonPrivacyPolicy.isSelected = true
        }

        binding.topBar.setOnBackButtonClickListener { finish() }
    }
}
