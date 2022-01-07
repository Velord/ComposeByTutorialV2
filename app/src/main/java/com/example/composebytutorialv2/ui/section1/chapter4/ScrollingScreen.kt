package com.example.composebytutorialv2.section1.chapter4

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.composebytutorialv2.R
import com.example.composebytutorialv2.navigation.BackButtonHandler
import com.example.composebytutorialv2.navigation.Screen
import com.example.composebytutorialv2.navigation.Section1Router

//two scrollable items on the same screen doesn't work/
//row does not work

@Preview
@Composable
fun ScrollingScreen() {
    MyScrollingScreenRow()
    MyScrollingScreenColumn()

    BackButtonHandler {
        Section1Router.navigateTo(Screen.Section1.Navigation)
    }
}

@Composable
fun MyScrollingScreenColumn(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .verticalScroll(
                state = rememberScrollState(),
                reverseScrolling = false
            )
            .fillMaxSize()
            .padding(vertical = 32.dp),
        horizontalAlignment = CenterHorizontally
    ) {
        BookImage(
            imageResId = R.drawable.round_accessibility_red_400_48dp,
            contDescResId = R.string.advanced_architecture_android
        )
        BookImage(
            imageResId = R.drawable.round_accessible_deep_purple_a200_48dp,
            contDescResId = R.string.kotlin_apprentice
        )
        BookImage(
            imageResId = R.drawable.round_analytics_blue_600_48dp,
            contDescResId = R.string.kotlin_coroutines
        )
    }
}

@Composable
fun MyScrollingScreenRow(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .horizontalScroll(
                state = rememberScrollState(),
                reverseScrolling = true
            )
            .fillMaxWidth(),
    ) {
        BookImage(
            imageResId = R.drawable.round_accessibility_red_400_48dp,
            contDescResId = R.string.advanced_architecture_android,
            size = 64.dp
        )
        BookImage(
            imageResId = R.drawable.round_accessible_deep_purple_a200_48dp,
            contDescResId = R.string.kotlin_apprentice,
            size = 64.dp
        )
        BookImage(
            imageResId = R.drawable.round_analytics_blue_600_48dp,
            contDescResId = R.string.kotlin_coroutines,
            size = 64.dp
        )
    }
}

@Composable
fun BookImage(
    @DrawableRes imageResId: Int,
    @StringRes contDescResId: Int,
    size: Dp = 616.dp
) {
    Image(
        imageVector = ImageVector.vectorResource(id = imageResId),
        contentDescription = stringResource(id = contDescResId),
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.size(476.dp, size)
            .padding(vertical = 8.dp, horizontal = 8.dp)
    )
}