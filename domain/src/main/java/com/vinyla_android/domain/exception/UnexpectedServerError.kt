package com.vinyla_android.domain.exception

class UnexpectedServerError @JvmOverloads constructor(
    message: String? = DEFAULT_MESSAGE,
    cause: Throwable? = null,
) : RuntimeException(message, cause) {

    companion object {
        private const val DEFAULT_MESSAGE = "알 수 없는 에러가 발생했습니다."
    }
}
