package com.vinyla_android.domain.event

import com.vinyla_android.presentation.utils.ToastMessage

sealed class SubmitEvent {
    object Success : SubmitEvent()

    class Fail(val message: String) : SubmitEvent() {
        constructor(t: Throwable) : this(t.message ?: ERROR_UNEXPECTED)

        fun getToastMessage(): ToastMessage = ToastMessage.StringValue(message)

        companion object {
            private const val ERROR_UNEXPECTED = "알 수 없는 에러가 발생했습니다"
        }
    }
}
