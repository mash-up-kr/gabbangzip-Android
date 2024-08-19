package com.mashup.gabbangzip.sharedalbum.presentation.utils

import androidx.compose.runtime.Immutable
import java.io.Serializable

@Immutable
class ImmutableList<T>(private val list: List<T>) : List<T> by list, Serializable
