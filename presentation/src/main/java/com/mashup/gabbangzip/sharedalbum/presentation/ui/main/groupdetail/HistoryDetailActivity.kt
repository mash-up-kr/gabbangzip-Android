package com.mashup.gabbangzip.sharedalbum.presentation.ui.main.groupdetail

import android.content.Context
import android.content.Intent
import android.graphics.Color.TRANSPARENT
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.mashup.gabbangzip.sharedalbum.presentation.theme.SharedAlbumTheme
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupKeyword
import com.mashup.gabbangzip.sharedalbum.presentation.utils.shareBitmap

class HistoryDetailActivity : ComponentActivity() {
    private val state by lazy { intent.getSerializableExtra(KEY_HISTORY) as HistoryDetailState? }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(
                TRANSPARENT,
            ),
        )
        setContent {
            SharedAlbumTheme {
                state?.let { state ->
                    Scaffold { innerPadding ->
                        HistoryDetailScreen(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color(0xFF555555)) // Todo : 추후 Design System 색상값 받기
                                .padding(innerPadding),
                            groupName = state.groupName,
                            keyword = GroupKeyword.getKeyword(state.keyword),
                            item = state.history,
                            onClickBackButton = { finish() },
                            onClickShareButton = { bitmap -> shareBitmap(bitmap) },
                        )
                    }
                }
            }
        }
    }

    companion object {
        private const val KEY_HISTORY = "history"

        fun openActivity(context: Context, state: HistoryDetailState) {
            context.startActivity(
                Intent(context, HistoryDetailActivity::class.java).apply {
                    putExtra(KEY_HISTORY, state)
                },
            )
        }
    }
}
