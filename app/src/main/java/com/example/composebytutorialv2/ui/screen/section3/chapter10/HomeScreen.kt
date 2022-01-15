package com.example.composebytutorialv2.ui.screen.section3.chapter10

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composebytutorialv2.data.model.section3.PostModel
import com.example.composebytutorialv2.data.model.section3.PostType
import com.example.composebytutorialv2.ui.component.section3.chapter10.ImagePost
import com.example.composebytutorialv2.ui.component.section3.chapter10.TextPost
import com.example.composebytutorialv2.ui.screen.section3.RedditViewModel

@Composable
fun HomeScreen(viewModel: RedditViewModel) {
    val posts: List<PostModel> by viewModel.allPosts.collectAsState(emptyList())

    LazyColumn(Modifier.background(color = MaterialTheme.colors.secondary)) {
        items(posts) {
            if (it.type == PostType.TEXT) TextPost(it)
            else ImagePost(it)
            Spacer(Modifier.height(6.dp))
        }
    }
}
