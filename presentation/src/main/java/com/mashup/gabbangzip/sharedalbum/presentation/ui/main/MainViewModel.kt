package com.mashup.gabbangzip.sharedalbum.presentation.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.messaging.FirebaseMessaging
import com.mashup.gabbangzip.sharedalbum.domain.model.notification.FcmNotificationParamDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.usecase.UploadMyPicUseCase
import com.mashup.gabbangzip.sharedalbum.domain.usecase.notification.RegisterFcmTokenUseCase
import com.mashup.gabbangzip.sharedalbum.domain.usecase.notification.SendFcmNotificationUseCase
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.model.MainEvent
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.model.MainUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val registerFcmTokenUseCase: RegisterFcmTokenUseCase,
    private val uploadMyPicUseCase: UploadMyPicUseCase,
    private val sendFcmNotificationUseCase: SendFcmNotificationUseCase,
) : ViewModel() {
    private var currentEventId: Long = -1

    private val _mainEvent = MutableSharedFlow<MainEvent>()
    val mainEvent = _mainEvent.asSharedFlow()

    private val _mainState = MutableStateFlow(MainUiState())
    val mainState = _mainState.asStateFlow()

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

    fun setCurrentEventId(eventId: Long) {
        currentEventId = eventId
    }

    fun uploadMyPic(fileList: List<File>) {
        updateLoadingState(isLoading = true)
        viewModelScope.launch {
            uploadMyPicUseCase(
                eventId = currentEventId,
                fileList = fileList,
            ).onSuccess {
                Log.d(TAG, "내 PIC 올리기 성공")
                _mainEvent.emit(MainEvent.SuccessUploadMyPic)
                updateLoadingState(isLoading = false)
            }.onFailure {
                Log.d(TAG, "내 PIC 올리기 실패")
                _mainEvent.emit(MainEvent.FailUploadMyPic)
                updateLoadingState(isLoading = false)
            }
        }
    }

    fun sendKookNotification(eventId: Long) {
        viewModelScope.launch {
            sendFcmNotificationUseCase(
                FcmNotificationParamDomainModel(
                    eventId = eventId,
                ),
            ).onSuccess {
                _mainEvent.emit(MainEvent.SuccessNotification)
            }.onFailure {
                _mainEvent.emit(MainEvent.FailNotification)
            }
        }
    }

    private fun updateLoadingState(isLoading: Boolean) {
        _mainState.update {
            it.copy(isLoading = isLoading)
        }
    }

    companion object {
        private const val TAG = "MainViewModel"
    }
}
