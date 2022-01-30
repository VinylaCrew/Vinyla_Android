package com.vinyla_android.data.repository

import com.google.common.truth.Truth.assertThat
import com.vinyla_android.data.source.VinylaMembersSource
import com.vinyla_android.domain.entity.member.SignUpInfo
import com.vinyla_android.domain.entity.member.SnsType
import com.vinyla_android.domain.entity.member.nickname.NicknameState
import com.vinyla_android.domain.repository.VinylaMembersRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Created By Malibin
 * on 10월 10, 2021
 */

internal class RealVinylaMembersRepositoryTest {

    private lateinit var vinylaMembersLocalSource: VinylaMembersSource
    private lateinit var vinylaMembersRemoteSource: VinylaMembersSource
    private lateinit var realVinylaMembersRepository: VinylaMembersRepository

    @BeforeEach
    fun setUp() {
        vinylaMembersLocalSource = mockk(relaxed = true)
        vinylaMembersRemoteSource = mockk(relaxed = true)
        realVinylaMembersRepository = RealVinylaMembersRepository(
            vinylaMembersLocalSource,
            vinylaMembersRemoteSource
        )
    }

    @Test
    fun `중복된 닉네임을 입력하면 중복 되었다는 결과가 나온다`(): Unit = runBlocking {
        // given
        coEvery {
            vinylaMembersRemoteSource.checkNickname("중복된 닉네임")
        } returns Result.success(NicknameState.DUPLICATED)

        // when
        val actualNicknameState = realVinylaMembersRepository.checkNickname("중복된 닉네임")

        // then
        assertThat(actualNicknameState).isEqualTo(NicknameState.DUPLICATED)
    }

    @Test
    fun `중복되지 않은 닉네임을 입력하면 사용 가능한 결과가 나온다`(): Unit = runBlocking {
        // given
        coEvery {
            vinylaMembersRemoteSource.checkNickname("닉네임")
        } returns Result.success(NicknameState.AVAILABLE)

        // when
        val actualNicknameState = realVinylaMembersRepository.checkNickname("닉네임")

        // then
        assertThat(actualNicknameState).isEqualTo(NicknameState.AVAILABLE)
    }

    @Test
    fun `회원 가입을 할 수 있다`(): Unit = runBlocking {
        // given
        val signupInfo = SignUpInfo(
            firebaseUid = "",
            nickname = "nickname",
            instagramId = "insta",
            profileImageUrl = "",
            snsType = SnsType.FACEBOOK,
            marketingAgreed = false,
            fcmToken = "",
        )
        val expectedResult = Result.success(Unit)
        coEvery { vinylaMembersRemoteSource.signUp(signupInfo) } returns expectedResult

        // when
        val signUpResult = realVinylaMembersRepository.signUp(signupInfo)

        // then
        assertThat(signUpResult).isEqualTo(expectedResult)
    }
}
