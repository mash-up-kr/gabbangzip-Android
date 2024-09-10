package com.mashup.gabbangzip.sharedalbum.presentation.utils

import androidx.navigation.NavController

fun NavController.canNavigateBack() = previousBackStackEntry != null
