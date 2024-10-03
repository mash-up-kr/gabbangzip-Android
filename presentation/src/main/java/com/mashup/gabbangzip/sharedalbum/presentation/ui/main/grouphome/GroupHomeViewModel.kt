package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.gabbangzip.sharedalbum.domain.usecase.group.GetGroupListUseCase
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.model.FilterTag
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.model.GroupHomeUiState
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.model.ViewType
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.model.toFilterTag
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.model.toUiModel
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupKeyword
import com.mashup.gabbangzip.sharedalbum.presentation.utils.ImmutableList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class GroupHomeViewModel @Inject constructor(
    getGroupListUseCase: GetGroupListUseCase,
) : ViewModel() {
    private val groupUiState = getGroupListUseCase()
    private val selectedTagFlow = MutableStateFlow(FilterTag.getTotalFilter())
    private val viewTypeFlow = MutableStateFlow(ViewType.List)
    private val optionFlow = selectedTagFlow.combine(viewTypeFlow) { selectedTag, viewType ->
        Pair(selectedTag, viewType)
    }

    val uiState = groupUiState.combine(optionFlow) { groupList, (selectedTag, viewType) ->
        if (groupList.isEmpty()) {
            GroupHomeUiState.NoGroup
        } else {
            GroupHomeUiState.GroupList(
                groupList = ImmutableList(
                    groupList.toUiModel().let {
                        if (selectedTag.name != GroupKeyword.TOTAL) {
                            it.filter { tag -> tag.keyword.name == selectedTag.name }
                        } else {
                            it
                        }
                    },
                ),
                filterTagList = ImmutableList(
                    listOf(
                        FilterTag.getTotalFilter(isSelected = GroupKeyword.TOTAL == selectedTag.name),
                    ) + groupList.map {
                        GroupKeyword
                            .getKeyword(keyword = it.keyword)
                            .toFilterTag(isSelected = it.keyword == selectedTag.name)
                    }.distinct(),
                ),
                viewType = viewType,
            )
        }
    }.catch {
        emit(
            GroupHomeUiState.Error(
                when (it) {
                    is UnknownHostException -> R.string.error_network
                    else -> R.string.error_server
                },
            ),
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = GroupHomeUiState.Loading,
    )

    fun clickedFilterTag(filterTag: FilterTag) {
        viewModelScope.launch {
            selectedTagFlow.emit(filterTag)
        }
    }

    fun clickedViewType(viewType: ViewType) {
        viewModelScope.launch {
            viewTypeFlow.emit(viewType)
        }
    }
}
