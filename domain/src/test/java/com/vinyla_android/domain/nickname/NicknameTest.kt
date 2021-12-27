package com.vinyla_android.domain.nickname

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class NicknameTest {

    @ValueSource(strings = ["말리빈", "malibin", "말12", "mal29", "말li2", "123"])
    @ParameterizedTest
    fun `유효한 닉네임을 검사할 수 있다`(validNickname: String) {
        // when
        val isNicknameValid = Nickname.isValid(validNickname)

        // then
        assertThat(isNicknameValid).isTrue()
    }

    @ValueSource(strings = ["일론ㄴㅂ머스크", "일", "ㅁㅁㄷ", "20자를넘어가는텍스트1234567890"])
    @ParameterizedTest
    fun `유효하지 않은 닉네임을 검사할 수 있다`(invalidNickname: String) {
        // when
        val isNicknameValid = Nickname.isValid(invalidNickname)

        // then
        assertThat(isNicknameValid).isFalse()
    }
}
