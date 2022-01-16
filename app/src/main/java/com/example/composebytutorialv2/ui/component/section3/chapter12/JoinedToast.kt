package com.example.composebytutorialv2.ui.component.section3.chapter12

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebytutorialv2.R

@Composable
fun JoinedToastView(visible: Boolean) {
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically { +40 } + fadeIn(),
        exit = slideOutVertically() + fadeOut()
    ) {
        ToastContent()
    }
}

@Composable
private fun ToastContent() {
    val shape = RoundedCornerShape(4.dp)

    Box(
        modifier = Modifier
            .clip(shape)
            .background(Color.White)
            .border(1.dp, Color.Black)
            .height(40.dp)
            .padding(horizontal = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.outline_airplane_ticket_red_400_48dp),
                contentDescription = stringResource(id = R.string.subreddit_icon)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = stringResource(id = R.string.you_have_joined_this_community))
        }
    }
}

@Preview
@Composable
private fun JoinedToastPreview() {
    JoinedToastView(visible = true)
}