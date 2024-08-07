package com.mashup.gabbangzip.sharedalbum.presentation.ui.main

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.messaging.FirebaseMessaging
import com.mashup.gabbangzip.sharedalbum.domain.usecase.PokeOtherMemberUseCase
import com.mashup.gabbangzip.sharedalbum.domain.usecase.UploadMyPicUseCase
import com.mashup.gabbangzip.sharedalbum.domain.usecase.notification.RegisterFcmTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val registerFcmTokenUseCase: RegisterFcmTokenUseCase,
    private val uploadMyPicUseCase: UploadMyPicUseCase,
    private val pokeOtherMemberUseCase: PokeOtherMemberUseCase,
) : ViewModel() {

    fun registerFcmToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@addOnCompleteListener
            }

            viewModelScope.launch {
                val token = task.result
                registerFcmTokenUseCase(token)
            }
        }
    }

    fun uploadMyPic(uri: Uri) {
        viewModelScope.launch {
            uploadMyPicUseCase()
        }
    }

    fun pokeOtherUser() {
        viewModelScope.launch {
            pokeOtherMemberUseCase()
        }
    }
}
