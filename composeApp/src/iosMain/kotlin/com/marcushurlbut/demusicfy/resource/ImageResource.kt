package com.marcushurlbut.demusicfy.resource

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import platform.UIKit.UIImage
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.ImageBitmap

@Composable
actual fun getAppIconResource(): Painter {
    val uiImage = UIImage.imageNamed("app_icon") // TODO: ensure available to iOS
    val imageBitmap = uiImage?.toImageBitmap()
    return imageBitmap?.let { BitmapPainter(it) }!!
}

fun UIImage.toImageBitmap(): ImageBitmap {
    return ImageBitmap(0, 0) // Placeholder: Replace with actual image conversion
}
