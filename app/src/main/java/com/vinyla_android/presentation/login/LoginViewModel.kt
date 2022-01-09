package com.vinyla_android.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.malibin.sns.auth.model.UserProfile
import com.vinyla_android.domain.event.SingleLiveEvent
import com.vinyla_android.domain.repository.VinylaMembersRepository
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val vinylaMembersRepository: VinylaMembersRepository,
) : ViewModel() {

    private val _isLoginSuccess = SingleLiveEvent<Unit>()
    val isLoginSuccess: LiveData<Unit> = _isLoginSuccess

    private val _isSignUpRequired = SingleLiveEvent<Unit>()
    val isSignUpRequired: LiveData<Unit> = _isSignUpRequired

    fun login(profile: UserProfile) {
        // SNS 로그인이 성공해서 호출 되는 것
        // 바이닐 서버에 로그인 요청
        // 로그인이 안되면 회원가입으로 보내야함
        // 토큰 저장
    }
}
