package com.vinyla_android.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.malibin.sns.auth.SnsAuth
import com.malibin.sns.auth.model.SnsType
import com.malibin.sns.auth.model.UserProfile
import com.vinyla_android.databinding.ActivityTempHomeBinding
import com.vinyla_android.presentation.login.LoginActivity

class TempHomeActivity : AppCompatActivity() {

    private var binding: ActivityTempHomeBinding? = null
    private var snsAuth: SnsAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTempHomeBinding.inflate(layoutInflater)
            .also { initView(it) }
        snsAuth = SnsAuth.getInstance()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        snsAuth = null
    }

    private fun initView(binding: ActivityTempHomeBinding) {
        setContentView(binding.root)
        val userProfile = getUserProfile()
        binding.textInfo.text = userProfile.toString()
        binding.buttonLogout.setOnClickListener { logout(userProfile.authType) }
        binding.buttonQuit.setOnClickListener { quit(userProfile.authType) }
    }

    /**
     * TODO 그냥 디비에 저장해서 상호 액티비티 간 의존성을 없애는 것도 좋다고 판단됨.
     */
    private fun getUserProfile(): UserProfile {
        return intent.getParcelableExtra("USER_PROFILE") as? UserProfile
            ?: error("user profile cannot be null")
    }

    private fun logout(type: SnsType) {
        snsAuth?.logout(type) { goLoginPage() }
    }

    private fun quit(type: SnsType) {
        snsAuth?.unlink(type) { goLoginPage() }
    }

    private fun goLoginPage() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}
