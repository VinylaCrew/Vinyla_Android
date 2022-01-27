package com.vinyla_android.domain.usecase.member

import com.vinyla_android.domain.entity.member.nickname.Nickname
import com.vinyla_android.domain.entity.member.nickname.NicknameState
import com.vinyla_android.domain.repository.VinylaMembersRepository
import javax.inject.Inject

class CheckNicknameStateUseCase @Inject constructor(
    private val vinylaMembersRepository: VinylaMembersRepository
) {
    suspend operator fun invoke(nickname: String): Result<NicknameState> {
        if(Nickname.isNotValid(nickname)) return Result.success(NicknameState.INVALID)
        return vinylaMembersRepository.checkNickname(nickname)
    }
}
