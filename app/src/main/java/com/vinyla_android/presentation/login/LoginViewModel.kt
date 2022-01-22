package com.vinyla_android.presentation.login

import android.util.Log
import androidx.lifecycle.LiveData
import com.malibin.sns.auth.SnsAuth
import com.malibin.sns.auth.model.UserProfile
import com.vinyla_android.domain.event.LoginEvent
import com.vinyla_android.domain.event.SingleLiveEvent
import com.vinyla_android.domain.repository.VinylaMembersRepository
import com.vinyla_android.presentation.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val vinylaMembersRepository: VinylaMembersRepository,
    private val snsAuth: SnsAuth,
) : BaseViewModel() {

    private val _loginEvent = SingleLiveEvent<LoginEvent>()
    val loginEvent: LiveData<LoginEvent> = _loginEvent

    fun login(profile: UserProfile) {
        // SNS 로그인이 성공해서 호출 되는 것
        // 바이닐 서버에 로그인 요청
        // 로그인이 안되면 회원가입으로 보내야함
        // 토큰 저장

        notifyLoading(false)
        _loginEvent.value = LoginEvent.Success
        Log.d("MalibinDebug" ,"login successed $profile")
    }
}
