package com.vinyla_android.presentation.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vinyla_android.databinding.ActivityLoginBinding
import com.vinyla_android.presentation.login.auth.SnsAuthManager

class LoginActivity : AppCompatActivity() {

    private var snsAuthManager: SnsAuthManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        snsAuthManager = SnsAuthManager(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        snsAuthManager = null
    }
}
