package com.vinyla_android.presentation.service

import com.google.firebase.messaging.FirebaseMessagingService
import com.vinyla_android.domain.repository.VinylaMembersRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class FcmService : FirebaseMessagingService() {

    @Inject
    lateinit var vinylaMembersRepository: VinylaMembersRepository

    override fun onNewToken(token: String) {
        CoroutineScope(Dispatchers.IO).launch {
            vinylaMembersRepository.saveFcmToken(token)
        }
    }
}
