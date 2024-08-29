package com.mashup.gabbangzip.sharedalbum.presentation.utils

import androidx.compose.foundation.Indication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.semantics.Role

fun Modifier.rippleClickable(
    enabled: Boolean = true,
    isHaptic: Boolean = false,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit,
): Modifier = composed {
    this then singleClickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = rememberRipple(),
        enabled = enabled,
        isHaptic = isHaptic,
        onClickLabel = onClickLabel,
        role = role,
        onClick = onClick,
    )
}

fun Modifier.noRippleClickable(
    enabled: Boolean = true,
    isHaptic: Boolean = false,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit,
): Modifier = composed {
    this then singleClickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = null,
        onClick = onClick,
        enabled = enabled,
        isHaptic = isHaptic,
        onClickLabel = onClickLabel,
        role = role,
    )
}

private fun Modifier.singleClickable(
    interactionSource: MutableInteractionSource,
    indication: Indication?,
    onClick: () -> Unit,
    isHaptic: Boolean,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    debounceMillis: Long = 300L,
): Modifier = composed {
    val hapticFeedback = LocalHapticFeedback.current
    multipleEventsCutter(debounceMillis = debounceMillis) { manager ->
        this then clickable(
            interactionSource = interactionSource,
            indication = indication,
            enabled = enabled,
            onClickLabel = onClickLabel,
            role = role,
            onClick = {
                if (isHaptic) {
                    hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                }
                manager.processEvent { onClick() }
            },
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
