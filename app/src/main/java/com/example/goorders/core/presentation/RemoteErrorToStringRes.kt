package com.example.goorders.core.presentation

import com.example.goorders.R
import com.example.goorders.core.domain.RemoteError
import com.example.goorders.core.domain.RemoteError.*
import com.example.goorders.core.domain.UiText

fun RemoteError.toUiText(): UiText {
    val stringRes = when (this) {
        REQUEST_TIMEOUT -> {
            R.string.app_name
        }
        TOO_MANY_REQUESTS -> {}
        NO_INTERNET -> {}
        SERVER -> {}
        SERIALIZATION -> {}
        UNKNOWN -> {}
        UNAUTHORIZED -> {}
        NOT_FOUND -> {}
    }

    return UiText.StringResourceId(stringRes as Int)
}