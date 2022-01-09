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
import com.vinyla_android.presentation.home.HomeActivity
import com.vinyla_android.presentation.signup.SignUpActivity
import com.vinyla_android.presentation.utils.printLog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private var binding: ActivityLoginBinding? = null
    private var snsAuth: SnsAuth? = SnsAuth.getInstance(this)

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
            .also { initView(it) }

        loginViewModel.isLoginSuccess.observe(this) {
            // 로그인이 성공한것임 바로 홈으로 보내야함
            Intent(this, HomeActivity::class.java)
                .also { startActivity(it) }
        }
        loginViewModel.isSignUpRequired.observe(this) {
            // sns 로그인은 성공했으나, 회원이 아닌것임. 회원가입으로 보내야함
            Intent(this, SignUpActivity::class.java)
                .also { startActivity(it) }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        snsAuth?.onActivityResult(requestCode, resultCode, data)
    }
//로그아웃하고 다시 누르면 또 권한 허용하겟느냐고 물어봄

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
        }
    }

    private fun login(type: SnsType) {
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
