package com.mashup.gabbangzip.sharedalbum.presentation.ui.invitation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.SharedAlbumTheme
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicSnackbarHost
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.model.PicSnackbarType
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.showPicSnackbar
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InvitationCodeActivity : ComponentActivity() {
    private val viewModel by viewModels<InvitationCodeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val state by viewModel.uiState.collectAsStateWithLifecycle()
            val snackbarHostState = remember { SnackbarHostState() }

            SharedAlbumTheme {
                Scaffold(
                    snackbarHost = {
                        PicSnackbarHost(state = snackbarHostState)
                    },
                ) { contentPadding ->
                    Box(modifier = Modifier.padding(contentPadding)) {
                        InvitationCodeScreen(
                            onBackButtonClicked = { finish() },
                            onNextButtonClicked = viewModel::enterGroupByCode,
                        )
                    }
                }

                when (state.isInvitationSuccessful) {
                    true -> navigateToMainActivity()
                    false -> {
                        LaunchedEffect(snackbarHostState) {
                            snackbarHostState.showPicSnackbar(
                                type = PicSnackbarType.WARNING,
                                message = getString(R.string.enter_group_by_code_failure_message),
                            )
                        }
                    }

                    null -> {}
                }
            }
        }
    }

    private fun navigateToMainActivity() {
        MainActivity.openActivity(
            context = this,
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP,
        )
        finish()
    }

    companion object {
        private const val TAG = "InvitationCodeActivity"

        fun openActivity(context: Context) {
            context.startActivity(
                Intent(context, InvitationCodeActivity::class.java),
            )
        }
    }
}
