package com.marcushurlbut.demusicfy.ui.view.appmenu.appcontainer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BottomMenuBar(
    onFirstButtonClick: () -> Unit = { },
    onSecondButtonClick: () -> Unit = { },
    onThirdButtonClick: () -> Unit = { },
    onFourthButtonClick: () -> Unit = { },
    firstButtonIcon: @Composable (() -> Unit)? = null,
    secondButtonIcon: @Composable (() -> Unit)? = null,
    thirdButtonIcon: @Composable (() -> Unit)? = null,
    fourthButtonIcon: @Composable (() -> Unit)? = null,
) {
    BottomAppBar(
        contentPadding = PaddingValues(4.dp),
        actions = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                if (firstButtonIcon != null) {
                    IconButton(
                        onClick = onFirstButtonClick,
                        content = firstButtonIcon
                    )
                }
                if (secondButtonIcon != null) {
                    IconButton(
                        onClick = onSecondButtonClick,
                        content = secondButtonIcon
                    )
                }
                if (thirdButtonIcon != null) {
                    IconButton(
                        onClick = onThirdButtonClick,
                        content = thirdButtonIcon
                    )
                }
                if (fourthButtonIcon != null) {
                    IconButton(
                        onClick = onFourthButtonClick,
                        content = fourthButtonIcon
                    )
                }
            }
        },
    )
}