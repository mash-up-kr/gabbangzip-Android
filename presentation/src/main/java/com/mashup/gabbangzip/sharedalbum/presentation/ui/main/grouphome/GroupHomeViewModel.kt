package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.gabbangzip.sharedalbum.domain.usecase.group.GetGroupListUseCase
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray100
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.model.FilterTagUiModel
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.grouphome.model.GroupHomeUiState
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
    private val filterTagUiState: MutableStateFlow<List<FilterTagUiModel>> = MutableStateFlow(
        listOf(
            FilterTagUiModel(
                GroupKeyword.TOTAL,
                null,
                Gray100,
                R.string.tag_total,
                true,
            ),
        ) +
            GroupKeyword.entries.map {
                FilterTagUiModel(
                    it.name,
                    it.symbolResId,
                    it.symbolColor,
                    it.tagNameResId,
                    false,
                )
            },
    )

    val uiState = groupUiState.combine(filterTagUiState) { groupList, filterTagList ->
        if (groupList.isEmpty()) {
            GroupHomeUiState.NoGroup
        } else {
            GroupHomeUiState.GroupList(
                groupList = ImmutableList(
                    groupList.toUiModel().let {
                        val selectedTag = filterTagList.find { tag -> tag.isSelected }
                        if (selectedTag?.name != GroupKeyword.TOTAL) {
                            ImmutableList(it.filter { tag -> tag.keyword.name == selectedTag?.name })
                        } else {
                            it
                        }
                    },
                ),
                filterTagList = ImmutableList(filterTagList),
            )
        }
    }
        .catch {
            emit(
                GroupHomeUiState.Error(
                    when (it) {
                        is UnknownHostException -> R.string.error_network
                        else -> R.string.error_server
                    },
                ),
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = GroupHomeUiState.Loading,
        )

    fun clickedFilterTag(filterTagUiModel: FilterTagUiModel) {
        viewModelScope.launch {
            filterTagUiState.emit(
                filterTagUiState.value.map {
                    it.copy(isSelected = it == filterTagUiModel)
                },
            )
        }
    }
}
