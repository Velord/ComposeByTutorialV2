package com.example.composebytutorialv2.ui.component.section3.chapter10.subreddit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebytutorialv2.R
import com.example.composebytutorialv2.ui.component.section3.chapter10.BackgroundTextView

private val mainCommunities = listOf(R.string.all, R.string.public_network)

private val communities = listOf(
    R.string.digitalnomad,
    R.string.covid19,
    R.string.memes,
    R.string.humor,
    R.string.worldnews,
    R.string.dogs,
    R.string.cats
)


@Composable
fun CommunityListView(modifier: Modifier = Modifier) {
    mainCommunities.forEach {
        CommunityView(text = stringResource(id = it))
    }

    Spacer(modifier.height(4.dp))

    BackgroundTextView(stringResource(id = R.string.communities))

    communities.forEach {
        CommunityView(text = stringResource(id = it))
    }
}

@Preview
@Composable
fun CommunitiesListPreview() {
    Column {
        CommunityListView()
    }
}