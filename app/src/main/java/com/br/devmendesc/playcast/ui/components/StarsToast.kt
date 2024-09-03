package com.br.devmendesc.playcast.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.pranavpandey.android.dynamic.toasts.DynamicToast

enum class ToastType {
    SUCCESS,
    ERROR,
    INFO
}

@Composable
fun StarsToast(type: ToastType, message: String) {
    val context = LocalContext.current


    LaunchedEffect(message) {
        when (type) {
            ToastType.SUCCESS -> DynamicToast.makeSuccess(context, message, 3000).show()
            ToastType.ERROR -> DynamicToast.makeError(context, message, 3000).show()
            ToastType.INFO -> DynamicToast.makeWarning(context, message, 3000).show()
        }

    }
}