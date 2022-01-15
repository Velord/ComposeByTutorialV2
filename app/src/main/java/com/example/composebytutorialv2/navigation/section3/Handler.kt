package com.example.composebytutorialv2.navigation.section3

import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalLifecycleOwner

private val localBackPressedDispatcher = staticCompositionLocalOf<OnBackPressedDispatcher?> { null }

@Composable
fun Handler(
    enabled: Boolean = true,
    onBackPressed: () -> Unit
) {
    val dispatcher = localBackPressedDispatcher.current ?: return
    val handler = remember {
        object : OnBackPressedCallback(enabled) {
            override fun handleOnBackPressed() {
                onBackPressed()
            }
        }
    }
    DisposableEffect(dispatcher) {
        dispatcher.addCallback(handler)
        onDispose {
            handler.remove()
        }
    }
}

@Composable
fun BackButtonAction(onBackPressed: () -> Unit) {
    val dispatcher = (LocalLifecycleOwner.current as ComponentActivity).onBackPressedDispatcher
    CompositionLocalProvider(localBackPressedDispatcher provides dispatcher) {
        Handler {
            onBackPressed()
        }
    }
}