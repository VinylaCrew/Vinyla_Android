package com.vinyla_android.presentation.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vinyla_android.data.model.UserProfile
import com.vinyla_android.databinding.ActivityLoginBinding
import com.vinyla_android.presentation.TempHomeActivity
import com.vinyla_android.presentation.login.auth.SnsAuth
import com.vinyla_android.presentation.login.auth.SnsAuthManager
import com.vinyla_android.presentation.utils.printLog
import com.vinyla_android.presentation.utils.showToast

class LoginActivity : AppCompatActivity() {

    private var binding: ActivityLoginBinding? = null
    private var snsAuthManager: SnsAuthManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
            .also { initView(it) }
        snsAuthManager = SnsAuthManager(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        snsAuthManager?.onActivityResult(requestCode, resultCode, data)
    }

    private fun initView(binding: ActivityLoginBinding) {
        setContentView(binding.root)
        binding.buttonKakao.setOnClickListener { proceedLoginOf(SnsAuth.Type.KAKAO) }
        binding.buttonFacebook.setOnClickListener { proceedLoginOf(SnsAuth.Type.FACEBOOK) }
        binding.buttonApple.setOnClickListener { proceedLoginOf(SnsAuth.Type.APPLE) }
    }

    private fun proceedLoginOf(type: SnsAuth.Type) {
        snsAuthManager?.login(type, ::onSnsResponse)
    }

    private fun onSnsResponse(profile: UserProfile?) {
        if (profile == null) {
            showToast("Sns 로그인에 실패했습니다.")
            printLog("몬가 SNS 로그인 실패함 profile : $profile")
            return
        }
        val intent = Intent(this, TempHomeActivity::class.java)
        intent.putExtra("USER_PROFILE", profile)
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        snsAuthManager = null
    }
}
