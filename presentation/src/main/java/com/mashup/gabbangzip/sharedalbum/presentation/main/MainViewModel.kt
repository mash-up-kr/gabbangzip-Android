package com.mashup.gabbangzip.sharedalbum.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.gabbangzip.sharedalbum.presentation.common.StartDestination
import com.mashup.gabbangzip.sharedalbum.presentation.common.topbar.TopBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Stack
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
) : ViewModel() {
    private val topBarStateStack: Stack<TopBarState> = Stack<TopBarState>().apply {
        push(StartDestination.TOP_BAR_STATE)
    }

    private val _topBarStateFlow: MutableStateFlow<TopBarState> =
        MutableStateFlow(StartDestination.TOP_BAR_STATE)
    val topBarStateFlow: StateFlow<TopBarState> get() = _topBarStateFlow

    fun updateTopBarState(topBarState: TopBarState) = viewModelScope.launch {
        _topBarStateFlow.emit(topBarState)
        topBarStateStack.push(topBarState)
    }

    fun popBackTopBarState() = viewModelScope.launch {
        if (topBarStateStack.isNotEmpty()) {
            topBarStateStack.pop()
        }
        if (topBarStateStack.isNotEmpty()) {
            topBarStateStack.peek()?.let { currentTopBarState ->
                _topBarStateFlow.emit(currentTopBarState)
            }
        }
    }
}
