package com.marcushurlbut.demusicfy.ui.view.appmenu

import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.marcushurlbut.demusicfy.ui.view.appmenu.appcontainer.AppScreen
import com.marcushurlbut.demusicfy.ui.view.appmenu.appcontainer.BottomMenuBar
import com.marcushurlbut.demusicfy.ui.view.appmenu.appcontainer.TopMenuBar

@Composable
fun AppContainer(
    drawerState: DrawerState,
    visibleBottomBar: Boolean = true,
    firstButtonIcon: @Composable (() -> Unit)? = null,
    onFirstButtonClick: () -> Unit = { },
    secondButtonIcon: @Composable (() -> Unit)? = null,
    onSecondButtonClick: () -> Unit = { },
    thirdButtonIcon: @Composable (() -> Unit)? = null,
    onThirdButtonClick: () -> Unit = { },
    fourthButtonIcon: @Composable (() -> Unit)? = null,
    onFourthButtonClick: () -> Unit = { },
    content: @Composable () -> Unit,
) {
    Scaffold(
        topBar = {
            TopMenuBar(drawerState)
        },
        bottomBar = {
            if (visibleBottomBar) {
                BottomMenuBar(
                    firstButtonIcon = firstButtonIcon,
                    onFirstButtonClick = onFirstButtonClick,
                    secondButtonIcon = secondButtonIcon,
                    onSecondButtonClick = onSecondButtonClick,
                    thirdButtonIcon = thirdButtonIcon,
                    onThirdButtonClick = onThirdButtonClick,
                    fourthButtonIcon = fourthButtonIcon,
                    onFourthButtonClick = onFourthButtonClick,
                )
            }
        }
    ) { innerPadding ->
        AppScreen(containerPadding = innerPadding)
        {
            content()
        }
    }
}