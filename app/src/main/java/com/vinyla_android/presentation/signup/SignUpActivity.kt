package com.vinyla_android.presentation.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.vinyla_android.databinding.ActivitySignUpBinding
import com.vinyla_android.presentation.utils.printLog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {

    private val signUpViewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewModel = signUpViewModel
        binding.lifecycleOwner = this

        signUpViewModel.nickname.observe(this){
            printLog("nickname = $it")
        }
        signUpViewModel.instagramId.observe(this){
            printLog("instagramId = $it")
        }
        signUpViewModel.isAllChecked.observe(this){
            printLog("isAllChecked = $it")
        }
        signUpViewModel.isTermsAndConditionOfServiceChecked.observe(this){
            printLog("isTermsAndConditionOfServiceChecked = $it")
        }
        signUpViewModel.isPrivacyPolicyChecked.observe(this){
            printLog("isPrivacyPolicyChecked = $it")
        }
        signUpViewModel.isMarketingChecked.observe(this){
            printLog("isMarketingChecked = $it")
        }
    }
}
