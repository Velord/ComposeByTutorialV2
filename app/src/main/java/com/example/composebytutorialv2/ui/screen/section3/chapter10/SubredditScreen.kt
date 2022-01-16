package com.example.composebytutorialv2.ui.screen.section3.chapter10

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composebytutorialv2.R
import com.example.composebytutorialv2.data.model.section3.SubredditModel
import com.example.composebytutorialv2.ui.component.section3.chapter10.subreddit.CommunityListView
import com.example.composebytutorialv2.ui.component.section3.chapter10.subreddit.SubredditView

private val subreddits = listOf(
    SubredditModel(
        R.string.raywenderlich,
        R.string.members_120k,
        R.string.welcome_to_raywenderlich
    ),
    SubredditModel(
        R.string.programming,
        R.string.members_600k,
        R.string.hello_programmers
    ),
    SubredditModel(
        R.string.android,
        R.string.members_400k,
        R.string.welcome_to_android
    ),
    SubredditModel(
        R.string.androiddev,
        R.string.members_500k,
        R.string.hello_android_devs
    )
)

@Composable
fun SubredditCollectionScreen(modifier: Modifier = Modifier) {
    Column(Modifier.verticalScroll(rememberScrollState())) {
        Text(
            modifier = modifier.padding(16.dp),
            text = stringResource(id = R.string.recently_visited_subreddits),
            fontSize = 12.sp,
            style = MaterialTheme.typography.subtitle1
        )

        LazyRow(modifier.padding(end = 16.dp)) {
            items(subreddits) { SubredditView(it) }
        }

        CommunityListView(modifier)
    }
}