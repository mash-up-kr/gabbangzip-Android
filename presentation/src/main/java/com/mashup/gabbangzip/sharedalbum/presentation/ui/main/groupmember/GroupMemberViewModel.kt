package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupmember

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class GroupMemberViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(GroupMemberUiState())
    val state = _state.asStateFlow()
}
