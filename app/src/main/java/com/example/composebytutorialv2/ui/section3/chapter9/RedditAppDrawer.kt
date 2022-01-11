package com.example.composebytutorialv2.ui.section3.chapter9

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.composebytutorialv2.R
import com.example.composebytutorialv2.ui.section3.chapter9.profileInfo.RedditProfileInfoView
import com.example.composebytutorialv2.ui.section3.theme.RedditSettings

@Composable
fun RedditAppDrawer(
    modifier: Modifier = Modifier,
    closeDrawerAction: () -> Unit = {}
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.surface)) {
        RedditAppDrawerHeader()

        RedditAppDrawerBody(closeDrawerAction)

        RedditAppDrawerFooter(modifier)
    }
}

@Composable
private fun RedditAppDrawerHeader() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            imageVector = Icons.Filled.AccountCircle,
            colorFilter = ColorFilter.tint(Color.LightGray),
            modifier = Modifier
                .padding(16.dp)
                .size(50.dp),
            contentScale = ContentScale.Fit,
            alignment = Alignment.Center,
            contentDescription = stringResource(id = R.string.account)
        )

        Text(
            text = stringResource(id = R.string.default_user_name),
            color = MaterialTheme.colors.primaryVariant
        )

        RedditProfileInfoView()
    }

    Divider(
        color = MaterialTheme.colors.onSurface.copy(alpha = .2f),
        modifier = Modifier
            .padding(16.dp)
            .padding(bottom = 0.dp)
    )
}

@Composable
private fun RedditAppDrawerBody(closeDrawerAction: () -> Unit) {
    Column {
        ScreenNavigationButtonView(
            icon = Icons.Filled.AccountBox,
            label = stringResource(id = R.string.my_profile),
            onClickAction = {
                closeDrawerAction()
            }
        )
        ScreenNavigationButtonView(
            icon = Icons.Filled.Home,
            label = stringResource(id = R.string.saved),
            onClickAction = {
                closeDrawerAction()
            }
        )
    }
}

@Composable
private fun RedditAppDrawerFooter(modifier: Modifier = Modifier) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(start = 0.dp)
    ) {
        val (settingsImage, settingsText, darkModeSwitcher) = createRefs()
        val colors = MaterialTheme.colors

        Icon(
            contentDescription = stringResource(R.string.settings),
            imageVector = Icons.Default.Settings,
            tint = colors.primaryVariant,
            modifier = modifier
                .constrainAs(settingsImage) {
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                }
                .padding(start = 16.dp)
        )

        Text(
            text = stringResource(R.string.settings),
            color = colors.primaryVariant,
            style = MaterialTheme.typography.body2,
            fontSize = 10.sp,
            modifier = modifier
                .padding(start = 16.dp)
                .constrainAs(settingsText) {
                    start.linkTo(settingsImage.end)
                    centerVerticallyTo(settingsImage)
                }
        )

        Icon(
            contentDescription = stringResource(R.string.change_theme),
            imageVector = ImageVector.vectorResource(id = R.drawable.outline_shield_moon_deep_purple_a100_48dp),
            tint = colors.primaryVariant,
            modifier = modifier.clickable { RedditSettings.switchTheme() }
                .constrainAs(darkModeSwitcher) {
                    end.linkTo(parent.end)
                    bottom.linkTo(settingsImage.bottom)
                }
                .padding(start = 16.dp)
        )
    }
}