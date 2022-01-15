package com.example.composebytutorialv2.ui.component.section3.chapter11

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composebytutorialv2.R
import com.example.composebytutorialv2.navigation.section3.RedditRouter

@Composable
fun ChooseCommunityTopBarView(modifier: Modifier = Modifier) {

    val colors = MaterialTheme.colors

    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.close),
                fontSize = 16.sp,
                color = colors.primaryVariant
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { RedditRouter.goBack() }
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    tint = colors.primaryVariant,
                    contentDescription = stringResource(id = R.string.close)
                )
            }
        },
        backgroundColor = colors.primary,
        elevation = 0.dp,
        modifier = modifier
            .height(48.dp)
            .background(Color.Blue)
    )
}

@Preview
@Composable
fun ChooseCommunityTopBarPreview() {
    ChooseCommunityTopBarView()
}