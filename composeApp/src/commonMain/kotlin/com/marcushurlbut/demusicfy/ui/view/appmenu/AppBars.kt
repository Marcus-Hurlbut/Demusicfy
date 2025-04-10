//package com.marcushurlbut.demusicfy.ui.view.appmenu
//
//import androidx.compose.material3.DrawerState
//import androidx.compose.material3.DrawerValue
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.rememberDrawerState
//import androidx.compose.runtime.Composable
//
//@Composable
//fun AppBars(
//    drawerState: DrawerState,
//    visibleBottomBar: Boolean = true,
//    firstButtonIcon: @Composable (() -> Unit)? = null,
//    onFirstButtonClick: () -> Unit = { },
//    secondButtonIcon: @Composable (() -> Unit)? = null,
//    onSecondButtonClick: () -> Unit = { },
//    thirdButtonIcon: @Composable (() -> Unit)? = null,
//    onThirdButtonClick: () -> Unit = { },
//    fourthButtonIcon: @Composable (() -> Unit)? = null,
//    onFourthButtonClick: () -> Unit = { },
//    content: @Composable () -> Unit
//) {
//    Scaffold(
//        topBar = {
//            AppBar(drawerState)
//        },
//        bottomBar = {
//            if (visibleBottomBar) {
//                BottomMenuBar(
//                    firstButtonIcon = firstButtonIcon,
//                    onFirstButtonClick = onFirstButtonClick,
//                    secondButtonIcon = secondButtonIcon,s
//                    onSecondButtonClick = onSecondButtonClick,
//                    thirdButtonIcon = thirdButtonIcon,
//                    onThirdButtonClick = onThirdButtonClick,
//                    fourthButtonIcon = fourthButtonIcon,
//                    onFourthButtonClick = onFourthButtonClick,
//                )
//            }
//        }
//    ) { innerPadding ->
//        AppContainer(
////            containerPadding = innerPadding,
//            drawerState = drawerState,
//        )
//        {
//            content()
//        }
//    }
//}