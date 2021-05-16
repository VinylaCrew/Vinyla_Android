package com.vinyla_android.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.app.ActivityOptionsCompat
import com.vinyla_android.R
import com.vinyla_android.databinding.ActivitySplashBinding
import com.vinyla_android.presentation.login.LoginActivity

class SplashActivity : AppCompatActivity() {

    private var binding: ActivitySplashBinding? = null
    private var willFinish: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
            .apply { initView(this) }
    }

    override fun onStop() {
        super.onStop()

        if (willFinish) finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun initView(binding: ActivitySplashBinding) {
        setContentView(binding.root)

        binding.root.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
                /* nothing */
            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
                /* nothing */
            }

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                fadeToLoginActivity()
            }

            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {
                /* nothing */
            }
        })
    }

    private fun fadeToLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        val bundle = ActivityOptionsCompat.makeCustomAnimation(
            this,
            R.anim.fade_in,
            R.anim.fade_nothing,
        ).toBundle()
        willFinish = true
        startActivity(intent, bundle)
    }
    /**
     * 1. 로그아웃 하면 다시 스플래시부터 시작해서 애니메이션 보여주고 로그인으로 넘어간다.
     * 2. 스플래시에서 로그인 정보가 존재하는 경우 애니메이션 없이 바로 메인으로 넘어간다.
     */
}
