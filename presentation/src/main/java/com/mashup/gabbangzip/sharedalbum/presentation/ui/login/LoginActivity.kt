package com.mashup.gabbangzip.sharedalbum.presentation.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.Gray80
import com.mashup.gabbangzip.sharedalbum.presentation.theme.PicTypography
import com.mashup.gabbangzip.sharedalbum.presentation.theme.SharedAlbumTheme
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicButton
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicPhotoCard
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.PicSnackbarHost
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.model.PicSnackbarType
import com.mashup.gabbangzip.sharedalbum.presentation.ui.common.showPicSnackbar
import com.mashup.gabbangzip.sharedalbum.presentation.ui.main.MainActivity
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupInfo
import com.mashup.gabbangzip.sharedalbum.presentation.ui.model.GroupKeyWord
import com.mashup.gabbangzip.sharedalbum.presentation.utils.ImmutableList
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val state by viewModel.uiState.collectAsStateWithLifecycle()
            val snackbarHostState = remember { SnackbarHostState() }

            SharedAlbumTheme {
                GroupCreationThumbnailScreen()

//                Scaffold(
//                    snackbarHost = {
//                        PicSnackbarHost(state = snackbarHostState)
//                    },
//                ) { contentPadding ->
//                    Box(modifier = Modifier.padding(contentPadding)) {
//                        LoginScreen(
//                            onClickLoginButton = {
//                                MainActivity.openActivity(this@LoginActivity)
//                                finish()
//                            },
//                        )
//                    }
//                }
//
//                if (state.errorMessage != null) {
//                    Log.d(TAG, "${state.errorMessage}")
//                    LaunchedEffect(snackbarHostState) {
//                        snackbarHostState.showPicSnackbar(
//                            type = PicSnackbarType.WARNING,
//                            message = getString(R.string.login_failure_message),
//                        )
//                    }
//                }
//
//                if (state.isUserLoggedIn) {
//                    MainActivity.openActivity(this)
//                    finish()
//                }
            }
        }
    }

    companion object {
        private const val TAG = "LoginActivity"

        fun openActivity(context: Activity) {
            context.startActivity(
                Intent(context, LoginActivity::class.java),
            )
        }
    }
}

@Composable
fun GroupCreationThumbnailScreen() {
    var isEnabled by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 24.dp),
            text = "그룹의 대표 사진을 추가해 주세요",
            textAlign = TextAlign.Center,
            color = Gray80,
            style = PicTypography.headBold18,
        )

        PicPhotoCard(
            modifier = Modifier.padding(horizontal = 41.dp),
            groupInfo = GroupInfo(
                1,
                "date",
                "groupName",
                "eventName",
                "https://blog.kakaocdn.net/dn/p2QMx/btq6Jyc0FGW/gionxyS48K13NeOUHBeTH0/img.jpg",
                "https://postfiles.pstatic.net/MjAyNDA2MjJfMjUw/MDAxNzE5MDQ1MDY5ODY1.xHsA7JX9CFP3ACodyCsb7CUZIllBZLrDuCddOVWpOVIg.AAfmzjGlhNYv0A_3N-GFNOBEN0fmcMsivwPn83JwSw4g.PNG/test.png?type=w580",
                "fourCutImageUrl",
                GroupKeyWord.SCHOOL,
                ImmutableList(listOf("")),
            ),
        )

        PicButton(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 21.dp),
            text = "다음",
            enable = isEnabled,
            isRippleClickable = true,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GroupCreationThumbnailScreenPreview() {
    GroupCreationThumbnailScreen()
}
