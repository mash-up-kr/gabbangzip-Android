package com.mashup.gabbangzip.sharedalbum.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.gabbangzip.sharedalbum.presentation.common.Route
import com.mashup.gabbangzip.sharedalbum.presentation.common.topbar.TopBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _topBarStateFlow: MutableStateFlow<TopBarState> =
        MutableStateFlow(TopBarState.initTopBarState)
    val topBarStateFlow: StateFlow<TopBarState> get() = _topBarStateFlow

    fun updateTopBar(routePath: String) = viewModelScope.launch {
        Route.getRoute(routePath)?.let { route ->
            updateTopBar(route)
        }
    }

    fun updateTopBar(route: Route) = viewModelScope.launch {
        _topBarStateFlow.emit(route.topBarState)
    }
}
