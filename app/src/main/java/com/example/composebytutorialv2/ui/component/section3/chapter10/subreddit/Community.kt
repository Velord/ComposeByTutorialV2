package com.example.composebytutorialv2.ui.component.section3.chapter10.subreddit

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composebytutorialv2.R

@Composable
fun CommunityView(
    text: String,
    modifier: Modifier = Modifier,
    onCommunityClick: () -> Unit = {}
) {
    Row(
        modifier
            .padding(start = 16.dp, top = 16.dp)
            .fillMaxWidth()
            .clickable(onClick = onCommunityClick)
    ) {
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.outline_perm_contact_calendar_black_24dp),
            contentDescription = stringResource(id = R.string.community_icon),
            modifier = modifier.size(24.dp).align(Alignment.CenterVertically)
        )
        Text(
            fontSize = 10.sp,
            color = MaterialTheme.colors.primaryVariant,
            text = text,
            fontWeight = FontWeight.Bold,
            modifier = modifier.padding(start = 16.dp).align(Alignment.CenterVertically)
        )
    }
}

@Preview
@Composable
fun CommunityPreview() {
    CommunityView(stringResource(id = R.string.raywenderlich_com))
}