package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail.model.HistoryItem

class EventHistoryProvider : PreviewParameterProvider<List<HistoryItem>> {
    override val values: Sequence<List<HistoryItem>>
        get() = sequenceOf(
            emptyList(),
            listOf(
                HistoryItem(title = "가빵집 MT1", date = "2024.11.03"),
            ),
            listOf(
                HistoryItem(title = "가빵집 MT1", date = "2024.11.03"),
                HistoryItem(title = "가빵집 MT2", date = "2024.11.03"),
                HistoryItem(title = "가빵집 MT3", date = "2024.11.03"),
            ),
            listOf(
                HistoryItem(title = "가빵집 MT1", date = "2024.11.03"),
                HistoryItem(title = "가빵집 MT2", date = "2024.11.03"),
                HistoryItem(title = "가빵집 MT3", date = "2024.11.03"),
                HistoryItem(title = "가빵집 MT4", date = "2024.11.03"),
                HistoryItem(title = "가빵집 MT5", date = "2024.11.03"),
                HistoryItem(title = "가빵집 MT6", date = "2024.11.03"),
                HistoryItem(title = "가빵집 MT7", date = "2024.11.03"),
                HistoryItem(title = "가빵집 MT8", date = "2024.11.03"),
                HistoryItem(title = "가빵집 MT9", date = "2024.11.03"),
                HistoryItem(title = "가빵집 MT0", date = "2024.11.03"),
            ),
        )
}
