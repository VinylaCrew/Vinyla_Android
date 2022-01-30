package com.vinyla_android.presentation.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.malibin.sns.auth.model.SnsType
import com.malibin.sns.auth.model.UserProfile
import com.vinyla_android.R
import com.vinyla_android.databinding.ActivityLoginBinding
import com.vinyla_android.domain.event.LoginEvent
import com.vinyla_android.presentation.home.HomeActivity
import com.vinyla_android.presentation.signup.SignUpActivity
import com.vinyla_android.presentation.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
            .apply {
                lifecycleOwner = this@LoginActivity
                viewModel = loginViewModel
            }
            .also { initView(it) }

        loginViewModel.loginEvent.observe(this) {
            when (it) {
                is LoginEvent.SignupNeeded -> deploySignUpActivity(it.userProfile)
                LoginEvent.Success -> deployHomeActivity()
                LoginEvent.Fail -> showLoginFail()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        loginViewModel.handleOnActivityResult(requestCode, resultCode, data)
    }

    private fun initView(binding: ActivityLoginBinding) {
        setContentView(binding.root)
        binding.buttonGoogle.setOnClickListener { login(SnsType.GOOGLE) }
        binding.buttonFacebook.setOnClickListener { login(SnsType.FACEBOOK) }
        binding.buttonApple.setOnClickListener {
            Toast.makeText(this, R.string.coming_soon, Toast.LENGTH_SHORT).show()
        }
    }

    private fun deploySignUpActivity(userProfile: UserProfile) {
        Intent(this, SignUpActivity::class.java)
            .putExtra(SignUpActivity.KEY_USER_PROFILE, userProfile)
            .also { startActivity(it) }
    }

    private fun deployHomeActivity() {
        Intent(this, HomeActivity::class.java)
            .also { startActivity(it) }
    }

    private fun showLoginFail() {
        showToast("로그인에 실패했네요 ㅠㅠ")
    }

    private fun login(type: SnsType) {
        loginViewModel.login(type, this)
    }
}
