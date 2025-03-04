package com.marcushurlbut.demusicfy.resource

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.marcushurlbut.demusicfy.R

@Composable
actual fun getAppIconResource(): Painter {
    return painterResource(id = R.drawable.app_icon)
}
