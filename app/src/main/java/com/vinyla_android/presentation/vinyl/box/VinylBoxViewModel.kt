package com.vinyla_android.presentation.vinyl.box

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinyla_android.domain.entity.Vinyl
import com.vinyla_android.domain.entity.Vinyls
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created By Malibin
 * on 8월 02, 2021
 */

@HiltViewModel
class VinylBoxViewModel @Inject constructor() : ViewModel() {

    private val _pagedCollectedVinyls = MutableLiveData<List<Vinyls>>()
    val pagedCollectedVinyls: LiveData<List<Vinyls>> = _pagedCollectedVinyls

    fun loadCollectedVinyls() = viewModelScope.launch {
        _pagedCollectedVinyls.value = STUB_VINYLS
    }

    companion object {
        private val STUB_VINYLS = listOf(
            Vinyls(
                listOf(
                    Vinyl(
                        id = 0,
                        name = "이이이름",
                        artiestName = "아티스트명ㅎ",
                        genre = "장르",
                        imageUrl = "",
                        thumbnailUrl = "",
                    ),
                    Vinyl(
                        id = 1,
                        name = "이이이름",
                        artiestName = "아티스트명ㅎ",
                        genre = "장르",
                        imageUrl = "",
                        thumbnailUrl = "",
                    ),
                    Vinyl(
                        id = 2,
                        name = "이이이름",
                        artiestName = "아티스트명ㅎ",
                        genre = "장르",
                        imageUrl = "",
                        thumbnailUrl = "",
                    ),
                    Vinyl(
                        id = 3,
                        name = "이이이름",
                        artiestName = "아티스트명ㅎ",
                        genre = "장르",
                        imageUrl = "",
                        thumbnailUrl = "",
                    ),
                    Vinyl(
                        id = 4,
                        name = "이이이름",
                        artiestName = "아티스트명ㅎ",
                        genre = "장르",
                        imageUrl = "",
                        thumbnailUrl = "",
                    ),
                    Vinyl(
                        id = 5,
                        name = "이이이름",
                        artiestName = "아티스트명ㅎ",
                        genre = "장르",
                        imageUrl = "",
                        thumbnailUrl = "",
                    ),
                    Vinyl(
                        id = 6,
                        name = "이이이름",
                        artiestName = "아티스트명ㅎ",
                        genre = "장르",
                        imageUrl = "",
                        thumbnailUrl = "",
                    ),
                    Vinyl(
                        id = 7,
                        name = "이이이름",
                        artiestName = "아티스트명ㅎ",
                        genre = "장르",
                        imageUrl = "",
                        thumbnailUrl = "",
                    ),
                    Vinyl(
                        id = 8,
                        name = "이이이름",
                        artiestName = "아티스트명ㅎ",
                        genre = "장르",
                        imageUrl = "",
                        thumbnailUrl = "",
                    ),
                )
            ),
            Vinyls(
                listOf(
                    Vinyl(
                        id = 10,
                        name = "이이이름",
                        artiestName = "아티스트명ㅎ",
                        genre = "장르",
                        imageUrl = "",
                        thumbnailUrl = "",
                    ),
                    Vinyl(
                        id = 11,
                        name = "이이이름",
                        artiestName = "아티스트명ㅎ",
                        genre = "장르",
                        imageUrl = "",
                        thumbnailUrl = "",
                    ),
                    Vinyl(
                        id = 12,
                        name = "이이이름",
                        artiestName = "아티스트명ㅎ",
                        genre = "장르",
                        imageUrl = "",
                        thumbnailUrl = "",
                    ),
                    Vinyl(
                        id = 13,
                        name = "이이이름",
                        artiestName = "아티스트명ㅎ",
                        genre = "장르",
                        imageUrl = "",
                        thumbnailUrl = "",
                    ),
                    Vinyl(
                        id = 14,
                        name = "이이이름",
                        artiestName = "아티스트명ㅎ",
                        genre = "장르",
                        imageUrl = "",
                        thumbnailUrl = "",
                    ),
                    Vinyl(
                        id = 15,
                        name = "이이이름",
                        artiestName = "아티스트명ㅎ",
                        genre = "장르",
                        imageUrl = "",
                        thumbnailUrl = "",
                    ),
                    Vinyl(
                        id = 16,
                        name = "이이이름",
                        artiestName = "아티스트명ㅎ",
                        genre = "장르",
                        imageUrl = "",
                        thumbnailUrl = "",
                    ),
                    Vinyl(
                        id = 17,
                        name = "이이이름",
                        artiestName = "아티스트명ㅎ",
                        genre = "장르",
                        imageUrl = "",
                        thumbnailUrl = "",
                    ),
                )
            ),
        )
    }
}
