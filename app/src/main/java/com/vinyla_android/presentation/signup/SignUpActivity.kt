package com.vinyla_android.presentation.signup

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.malibin.sns.auth.model.UserProfile
import com.vinyla_android.databinding.ActivitySignUpBinding
import com.vinyla_android.presentation.home.HomeActivity
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

        initView(binding)

        signUpViewModel.isSignUpSuccess.observe(this) {
            deployHomeActivity()
        }
    }

    private fun getUserProfile(): UserProfile = intent.getParcelableExtra(KEY_USER_PROFILE)
        ?: error("user profile cannot be null")

    private fun initView(binding: ActivitySignUpBinding) {
        binding.topBar.setOnBackButtonClickListener { finish() }
        signUpViewModel.loadProfile(getUserProfile())
    }

    private fun deployHomeActivity() {
        Intent(this, HomeActivity::class.java)
            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            .also { startActivity(it) }
    }

    companion object {
        const val KEY_USER_PROFILE = "KEY_USER_PROFILE"
    }
}
