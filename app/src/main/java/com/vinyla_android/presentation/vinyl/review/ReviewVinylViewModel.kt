package com.vinyla_android.presentation.vinyl.review

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.vinyla_android.domain.entity.Vinyl
import com.vinyla_android.domain.repository.VinylsRepository
import com.vinyla_android.presentation.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReviewVinylViewModel @Inject constructor(
    private val vinylsRepository: VinylsRepository,
) : BaseViewModel() {

    private val _vinyl = MutableLiveData<Vinyl>()
    val vinyl: LiveData<Vinyl> = _vinyl

    val starScore = MutableLiveData<Float>(DEFAULT_STAR_SCORE)
    val shownStarScore: LiveData<Int> = MediatorLiveData<Int>().apply {
        addSource(starScore) { value = (it * 2).toInt() }
    }

    val reviewContent = MutableLiveData("")

    fun loadVinyl(vinylId: Int) = launchViewModelScopeWithLoading {
        _vinyl.value = vinylsRepository.getVinylOf(vinylId)
    }

    fun saveVinylCollection() = launchViewModelScopeWithLoading {
        val score = starScore.value ?: DEFAULT_STAR_SCORE
        val reviewContent = reviewContent.value.orEmpty()

    }

    companion object {
        private const val DEFAULT_STAR_SCORE = 0.0f
    }
}
