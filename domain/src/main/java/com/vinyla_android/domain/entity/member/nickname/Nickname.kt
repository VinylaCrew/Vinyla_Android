package com.vinyla_android.domain.entity.member.nickname

class Nickname {

    companion object {
        private val NICKNAME_LENGTH_RANGE = 2..20
        private val NICKNAME_FORMAT = Regex("^[가-힣a-zA-Z0-9]+")

        fun isValid(nickname: String): Boolean {
            return NICKNAME_FORMAT.matches(nickname) && nickname.length in NICKNAME_LENGTH_RANGE
        }
    }
}
