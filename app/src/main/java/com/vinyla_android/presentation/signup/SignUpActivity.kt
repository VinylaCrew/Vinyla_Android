package com.vinyla_android.presentation.signup

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.vinyla_android.databinding.ActivitySignUpBinding
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
    }

    private fun getSnsNickname(): String = intent.getStringExtra(KEY_NICKNAME).orEmpty()

    private fun initView(binding: ActivitySignUpBinding) {
        binding.topBar.setOnBackButtonClickListener { finish() }
        signUpViewModel.loadNickname(getSnsNickname())
    }

    companion object {
        const val KEY_NICKNAME = "KEY_NICKNAME"
    }
}
