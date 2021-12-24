package com.vinyla_android.presentation.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.malibin.sns.auth.SnsAuth
import com.malibin.sns.auth.model.SnsType
import com.malibin.sns.auth.model.UserProfile
import com.vinyla_android.R
import com.vinyla_android.databinding.ActivityLoginBinding
import com.vinyla_android.presentation.TempHomeActivity
import com.vinyla_android.presentation.utils.printLog

class LoginActivity : AppCompatActivity() {

    private var binding: ActivityLoginBinding? = null
    private var snsAuthManager: SnsAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
            .also { initView(it) }
//        snsAuthManager = SnsAuth()
        // 모듈 분리로 다시 만들어야함!!!!!
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        snsAuthManager?.onActivityResult(requestCode, resultCode, data)
    }
//로그아웃하고 다시 누르면 또 권한 허용하겟느냐고 물어봄

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        snsAuthManager = null
    }

    private fun initView(binding: ActivityLoginBinding) {
        setContentView(binding.root)
        binding.buttonGoogle.setOnClickListener { login(SnsType.KAKAO) }
        binding.buttonFacebook.setOnClickListener { login(SnsType.FACEBOOK) }
        binding.buttonApple.setOnClickListener {
            Toast.makeText(this, R.string.coming_soon, Toast.LENGTH_SHORT).show()
        }
    }

    private fun login(type: SnsType) {
//        snsAuthManager?.login(this, type, ::onSnsResponse)
    }

    private fun onSnsResponse(profile: UserProfile?) {
        if (profile == null) {
            printLog("몬가 SNS 로그인 실패함 profile : $profile")
            return
        }
        val intent = Intent(this, TempHomeActivity::class.java)
        intent.putExtra("USER_PROFILE", profile)
        startActivity(intent)
        finish()
    }
}
