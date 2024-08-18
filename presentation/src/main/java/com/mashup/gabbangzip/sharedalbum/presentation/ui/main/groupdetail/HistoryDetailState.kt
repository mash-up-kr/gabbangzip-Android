package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail

import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail.model.HistoryItem
import java.io.Serializable

data class HistoryDetailState(
    val groupName: String,
    val keyword: String,
    val history: HistoryItem,
) : Serializable
