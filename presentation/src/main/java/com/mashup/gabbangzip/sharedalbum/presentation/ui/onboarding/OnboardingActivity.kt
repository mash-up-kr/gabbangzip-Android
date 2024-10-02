package com.mashup.gabbangzip.sharedalbum.presentation.ui.onboarding

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.mashup.gabbangzip.sharedalbum.presentation.R
import com.mashup.gabbangzip.sharedalbum.presentation.theme.SharedAlbumTheme
import com.mashup.gabbangzip.sharedalbum.presentation.ui.invitation.InvitationCodeViewModel
import com.mashup.gabbangzip.sharedalbum.presentation.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingActivity : ComponentActivity() {
    private val viewModel by viewModels<OnboardingViewModel>()
    private val pageResourceIdList = listOf(
        R.drawable.onboarding_1,
        R.drawable.onboarding_2,
        R.drawable.onboarding_3,
        R.drawable.onboarding_4,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            SystemBarStyle.light(
                Color.TRANSPARENT,
                Color.TRANSPARENT,
            ),
        )
        setContent {
            SharedAlbumTheme {
                Scaffold { contentPadding ->
                    Box(modifier = Modifier.padding(contentPadding)) {
                        OnboardingScreen(
                            pageResourceIdList = pageResourceIdList,
                            onClickStart = {
                                viewModel.saveIsNotFirstOpen()
                                LoginActivity.openActivity(this@OnboardingActivity)
                            },
                        )
                    }
                }
            }
        }
    }

    companion object {
        fun openActivity(context: Activity) {
            context.startActivity(
                Intent(context, OnboardingActivity::class.java),
            )
        }
    }
}
