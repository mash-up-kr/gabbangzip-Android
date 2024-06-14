package com.mashup.gabbangzip.sharedalbum.presentation.login.navigation

import androidx.activity.compose.BackHandler
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mashup.gabbangzip.sharedalbum.presentation.common.BaseRoute
import com.mashup.gabbangzip.sharedalbum.presentation.login.LoginScreen

fun NavController.navigateLogin(navOptions: NavOptions) {
    navigate(LoginRoute.route, navOptions)
}

fun NavGraphBuilder.loginNavGraph(
    onLoginClick: () -> Unit,
    onBackPressed: () -> Unit,
) {
    composable(route = LoginRoute.route) {
        LoginScreen(onLoginClick = onLoginClick)
        BackHandler {
            onBackPressed()
        }
    }
}

data object LoginRoute : BaseRoute {
    override val route: String = "login"
}
