package com.example.composebytutorialv2.ui.component.section3.chapter11

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composebytutorialv2.navigation.section3.RedditRouter
import com.example.composebytutorialv2.ui.component.section3.chapter10.subreddit.CommunityView
import com.example.composebytutorialv2.ui.screen.section3.RedditViewModel

private val defaultCommunities = listOf("raywenderlich", "androiddev", "puppies")

@Composable
fun SearchedCommunitiesListView(
    communities: List<String>,
    viewModel: RedditViewModel?,
    modifier: Modifier = Modifier
) {
    viewModel?.let {
        communities.forEach {
            CommunityView(
                text = it,
                modifier = modifier,
                onCommunityClick = {
                    viewModel.selectCommunity(it)
                    RedditRouter.goBack()
                }
            )
        }
    }
}

@Preview
@Composable
fun SearchedCommunitiesListPreview() {
    Column {
        SearchedCommunitiesListView(communities = defaultCommunities, viewModel = null)
    }
}