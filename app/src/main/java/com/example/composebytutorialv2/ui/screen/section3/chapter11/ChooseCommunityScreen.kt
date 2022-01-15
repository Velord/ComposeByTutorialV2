package com.example.composebytutorialv2.ui.screen.section3.chapter11

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.composebytutorialv2.R
import com.example.composebytutorialv2.navigation.section3.RedditRouter
import com.example.composebytutorialv2.ui.component.section3.chapter11.ChooseCommunityTopBarView
import com.example.composebytutorialv2.ui.component.section3.chapter11.SearchedCommunitiesListView
import com.example.composebytutorialv2.ui.screen.section3.RedditViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

private const val SEARCH_DELAY_MILLIS = 300L

@Composable
fun ChooseCommunityScreen(viewModel: RedditViewModel, modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    val communities by viewModel.subreddits.collectAsState()
    var searchedText by remember { mutableStateOf("") }
    var currentJob by remember { mutableStateOf<Job?>(null) }
    val activeColor = MaterialTheme.colors.onSurface

    LaunchedEffect(Unit) {
        viewModel.searchCommunities(searchedText)
    }

    Column {
        ChooseCommunityTopBarView()
        TextField(
            value = searchedText,
            onValueChange = {
                searchedText = it
                currentJob?.cancel()
                currentJob = scope.async {
                    delay(SEARCH_DELAY_MILLIS)
                    viewModel.searchCommunities(searchedText)
                }
            },
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = stringResource(id = R.string.search))
            },
            label = { Text(stringResource(id = R.string.search)) },
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = activeColor,
                focusedLabelColor = activeColor,
                cursorColor = activeColor,
                backgroundColor = MaterialTheme.colors.surface
            )
        )
        SearchedCommunitiesListView(communities, viewModel, modifier)
    }

    BackHandler {
        RedditRouter.goBack()
    }
}