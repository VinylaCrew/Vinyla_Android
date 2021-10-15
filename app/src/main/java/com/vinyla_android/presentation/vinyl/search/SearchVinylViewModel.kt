package com.vinyla_android.presentation.vinyl.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.vinyla_android.domain.entity.SimpleVinyl
import com.vinyla_android.domain.repository.VinylsRepository
import com.vinyla_android.presentation.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created By Malibin
 * on 10월 11, 2021
 */

@HiltViewModel
class SearchVinylViewModel @Inject constructor(
    private val vinylsRepository: VinylsRepository,
) : BaseViewModel() {
    val query = MutableLiveData("")

    private val _searchedVinyls = MutableLiveData<List<SimpleVinyl>>()
    val searchedVinyls: LiveData<List<SimpleVinyl>> = _searchedVinyls

    fun searchVinyls() = viewModelScope.launch {
        notifyLoading(true)
        _searchedVinyls.value = vinylsRepository.searchVinyls(query.value.orEmpty())
    }.invokeOnCompletion { notifyLoading(false) }

    fun clearQuery() {
        query.value = ""
    }
}
