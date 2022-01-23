package com.vinyla_android.presentation.login

import android.content.Intent
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import com.malibin.sns.auth.SnsAuth
import com.malibin.sns.auth.model.SnsType
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

    /**
     * 와 진심 이렇게 만들기 너무 싫은데 시간 비용 타협하기로함.
     * 이놈의 google auth 때문에 어쩔 수가 없음 빌어먹을놈에 sns로그인
     */
    fun login(snsType: SnsType, activity: FragmentActivity) {
        notifyLoading(true)
        snsAuth.login(snsType, activity, ::onSnsResponse)
    }

    private fun onSnsResponse(profile: UserProfile?) {
        if (profile == null) {
            _loginEvent.value = LoginEvent.Fail
            return
        }
        // 바닐라 서버에 이 정보로 회원가입을 했었는지 물어봐야함
//        _loginEvent.value = LoginEvent.Success
        _loginEvent.value = LoginEvent.SignupNeeded(profile)
        notifyLoading(false)
    }

    fun handleOnActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        snsAuth.onActivityResult(requestCode, resultCode, data)
    }
}
