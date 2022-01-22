package com.vinyla_android.presentation.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.malibin.sns.auth.SnsAuth
import com.malibin.sns.auth.model.SnsType
import com.malibin.sns.auth.model.UserProfile
import com.vinyla_android.R
import com.vinyla_android.databinding.ActivityLoginBinding
import com.vinyla_android.domain.event.LoginEvent
import com.vinyla_android.presentation.home.HomeActivity
import com.vinyla_android.presentation.signup.SignUpActivity
import com.vinyla_android.presentation.utils.printLog
import com.vinyla_android.presentation.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private var binding: ActivityLoginBinding? = null
    private var snsAuth: SnsAuth? = SnsAuth.getInstance()

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
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
        snsAuth?.onActivityResult(requestCode, resultCode, data)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        snsAuth = null
    }

    private fun initView(binding: ActivityLoginBinding) {
        setContentView(binding.root)
        binding.buttonGoogle.setOnClickListener { login(SnsType.GOOGLE) }
        binding.buttonFacebook.setOnClickListener { login(SnsType.FACEBOOK) }
        binding.buttonApple.setOnClickListener {
            Toast.makeText(this, R.string.coming_soon, Toast.LENGTH_SHORT).show()
            snsAuth?.unlink(SnsType.FACEBOOK) {
                printLog("unlinked")
            }
        }
    }

    private fun deploySignUpActivity(userProfile: UserProfile) {
        Intent(this, SignUpActivity::class.java)
            .putExtra(SignUpActivity.KEY_NICKNAME, userProfile.nickname)
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
        loginViewModel.notifyLoading(true)
        snsAuth?.login(type, this, ::onSnsResponse)
    }

    private fun onSnsResponse(profile: UserProfile?) {
        if (profile == null) {
            printLog("몬가 SNS 로그인 실패함 profile : $profile")
            return
        }
        loginViewModel.login(profile)
    }
}
