package com.vinyla_android.presentation.vinyl.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinyla_android.domain.entity.SimpleVinyl
import com.vinyla_android.domain.repository.VinylsRepository
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
) : ViewModel() {
    val query = MutableLiveData("")

    private val _searchedVinyls = MutableLiveData<List<SimpleVinyl>>()
    val searchedVinyls: LiveData<List<SimpleVinyl>> = _searchedVinyls

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    fun searchVinyls() = viewModelScope.launch {
        _isLoading.value = true
        _searchedVinyls.value = vinylsRepository.searchVinyls(query.value.orEmpty())

    }.invokeOnCompletion { _isLoading.value = false }

    fun clearQuery() {
        query.value = ""
    }
}
