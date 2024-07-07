package com.mashup.gabbangzip.sharedalbum.presentation.utils

import androidx.compose.foundation.Indication
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.semantics.Role

fun Modifier.rippleClickable(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit,
): Modifier = composed {
    this then singleClickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = rememberRipple(),
        enabled = enabled,
        onClickLabel = onClickLabel,
        role = role,
        onClick = onClick,
    )
}

fun Modifier.noRippleClickable(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit,
): Modifier = composed {
    this then singleClickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = null,
        enabled = enabled,
        onClickLabel = onClickLabel,
        role = role,
        onClick = onClick,
    )
}

fun Modifier.singleClickable(
    onClick: () -> Unit,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    debounceMillis: Long = 300L,
): Modifier = composed {
    multipleEventsCutter(debounceMillis = debounceMillis) { manager ->
        this then singleClickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = LocalIndication.current,
            onClick = { manager.processEvent { onClick() } },
            enabled = enabled,
            onClickLabel = onClickLabel,
            role = role,
            debounceMillis = debounceMillis,
        )
    }
}

fun Modifier.singleClickable(
    interactionSource: MutableInteractionSource,
    indication: Indication?,
    onClick: () -> Unit,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    debounceMillis: Long = 300L,
): Modifier = composed {
    multipleEventsCutter(debounceMillis = debounceMillis) { manager ->
        this then clickable(
            interactionSource = interactionSource,
            indication = indication,
            enabled = enabled,
            onClickLabel = onClickLabel,
            role = role,
            onClick = { manager.processEvent { onClick() } },
        )
    }
}

fun Modifier.hideKeyboardOnOutsideClicked(): Modifier = composed {
    val focusManager = LocalFocusManager.current
    this then pointerInput(Unit) {
        detectTapGestures(
            onTap = { focusManager.clearFocus() },
        )
    }
}
