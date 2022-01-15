package com.example.composebytutorialv2.ui.component.section3.chapter10.subreddit

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composebytutorialv2.R

@Composable
fun SubredditImageView(modifier: Modifier) {
    Image(
        painter = ColorPainter(Color.Blue),
        contentDescription = stringResource(id = R.string.subreddit_image),
        modifier = modifier.fillMaxWidth().height(30.dp)
    )
}

@Composable
fun SubredditIconView(modifier: Modifier) {
    Icon(
        modifier = modifier,
        tint = Color.LightGray,
        imageVector = ImageVector.vectorResource(id = R.drawable.baseline_airplanemode_active_purple_a700_48dp),
        contentDescription = stringResource(id = R.string.subreddit_icon),
    )
}

@Composable
fun SubredditNameView(modifier: Modifier, @StringRes nameStringRes: Int) {
    Text(
        fontWeight = FontWeight.Bold,
        fontSize = 10.sp,
        text = stringResource(nameStringRes),
        color = MaterialTheme.colors.primaryVariant,
        modifier = modifier.padding(4.dp)
    )
}

@Composable
fun SubredditMembersView(modifier: Modifier, @StringRes membersStringRes: Int) {
    Text(
        fontSize = 8.sp,
        text = stringResource(membersStringRes),
        color = Color.Gray,
        modifier = modifier
    )
}

@Composable
fun SubredditDescriptionView(modifier: Modifier, @StringRes descriptionStringRes: Int) {
    Text(
        fontSize = 8.sp,
        text = stringResource(descriptionStringRes),
        color = MaterialTheme.colors.primaryVariant,
        modifier = modifier.padding(4.dp)
    )
}