package com.vinyla_android.presentation.vinyl.level

import com.vinyla_android.R

enum class VinylLevel(
    val level: Int,
    val title: String,
    val description: String,
    val minCollectionCount: Int,
    val imageResId: Int,
) {
    ONE(1, "닐페이스 (0~1)", "바이닐과 밀당의 시작", 0, R.drawable.ic_level_1),
    TWO(2, "닐리즈 (1~9)", "바이닐 수집의 매력을 알게된 리즈시절", 1, R.drawable.ic_level_2),
    THREE(3, "닐스터 (10~49)", "바이닐 수집에 진심인 힙스터", 10, R.drawable.ic_level_3),
    FOUR(4, "닐암스트롱 (50~499)", "바이닐 정복에 첫 발을 디딘 콜렉터", 50, R.drawable.ic_level_4),
    FIVE(5, "닐라대왕 (500~)", "이 세상 모든 바이닐을 손에 넣은 레전드", 500, R.drawable.ic_level_5);

    companion object {
        fun all(): List<VinylLevel> = values().toList()
    }
}
