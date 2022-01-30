package com.vinyla_android.domain.usecase.member

import com.vinyla_android.domain.entity.member.SignUpInfo
import com.vinyla_android.domain.entity.member.SnsType
import com.vinyla_android.domain.entity.member.VinylaToken
import com.vinyla_android.domain.repository.VinylaMembersRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val vinylaMembersRepository: VinylaMembersRepository
) {
    suspend operator fun invoke(
        firebaseUid: String,
        nickname: String,
        instagramId: String,
        profileImageUrl: String,
        snsType: SnsType,
        marketingAgreed: Boolean
    ): Result<VinylaToken> {
        val signupParams = SignUpInfo(
            firebaseUid = firebaseUid,
            nickname = nickname,
            instagramId = instagramId,
            profileImageUrl = profileImageUrl,
            snsType = snsType,
            marketingAgreed = marketingAgreed,
            fcmToken = vinylaMembersRepository.getFcmToken().getOrDefault(""),
        )
        return vinylaMembersRepository.signUp(signupParams)
            .onSuccess {
                vinylaMembersRepository.saveMarketingAgreed(marketingAgreed)
                vinylaMembersRepository.saveNickname(nickname)
                vinylaMembersRepository.saveLoginSnsType(snsType)
                vinylaMembersRepository.saveVinylaToken(it.token)
            }
    }
}
