package com.example.moniepointassessment.ui.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.moniepointassessment.ui.component.BottomNav
import com.example.moniepointassessment.ui.component.HomeAppBar
import com.example.moniepointassessment.ui.component.SearchQueryList
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreen(
    showSearchResultsOnly: Boolean = false,
    onMenuItemClicked: (String) -> Unit = {},
    onReadyToSearch: (Boolean) -> Unit = {}
) {
    val backgroundColor = Color(0xFFF8F8F8)
    val systemBarColor = Color(0xFF435097)
    val systemUiController = rememberSystemUiController()
    var searchQuery by remember { mutableStateOf("") }

    if (!showSearchResultsOnly) {
        LocalFocusManager.current.clearFocus(true)
    }
    var showAnimations by remember { mutableStateOf(false) }

    SideEffect {
        showAnimations = true
        systemUiController.setSystemBarsColor(
            color = systemBarColor,
            darkIcons = false
        )
        systemUiController.setNavigationBarColor(
            color = backgroundColor,
            darkIcons = false
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (topAppBar, middleSection, bottomAppBar) = createRefs()

            val topModifier = Modifier.constrainAs(topAppBar) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }

            val middleModifier = Modifier.constrainAs(middleSection) {
                top.linkTo(topAppBar.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(bottomAppBar.top)
                height = Dimension.fillToConstraints
            }

            val bottomModifier = Modifier.constrainAs(bottomAppBar) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }

            AnimatedVisibility(
                visible = showAnimations,
                modifier = topModifier,
                enter = slideInVertically(
                    initialOffsetY = { -it },
                    animationSpec = tween(durationMillis = 500, easing = LinearEasing)
                )
            ) {
                HomeAppBar(
                    modifier = topModifier,
                    searchQuery = searchQuery,
                    showSearchResultsOnly = showSearchResultsOnly,
                    onReadyToSearch = { focused ->
                        onReadyToSearch(focused)
                        if (!focused) searchQuery = ""
                    },
                    onSearchQueryChanged = { searchQuery = it }
                )
            }

            AnimatedVisibility(
                visible = showSearchResultsOnly,
                modifier = middleModifier,
                enter = slideInVertically(
                    initialOffsetY = { it },
                    animationSpec = tween(durationMillis = 300, easing = LinearEasing)
                )
            ) {
                SearchQueryList(modifier = middleModifier, query = searchQuery)
            }

            if (!showSearchResultsOnly) {
                AnimatedVisibility(visible = showAnimations, modifier = middleModifier) {
                    MainContent(
                        middleModifier
                    )
                }

                AnimatedVisibility(
                    visible = showAnimations,
                    modifier = bottomModifier,
                    enter = slideInVertically(
                        initialOffsetY = { it },
                        animationSpec = tween(durationMillis = 500, easing = LinearEasing)
                    )
                ) {
                    BottomNav(bottomModifier, onMenuItemClicked)
                }
            }
        }
    }
}
