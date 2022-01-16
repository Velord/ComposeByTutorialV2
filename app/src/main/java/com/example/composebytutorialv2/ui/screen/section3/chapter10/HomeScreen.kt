package com.example.composebytutorialv2.ui.screen.section3.chapter10

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composebytutorialv2.data.model.section3.PostModel
import com.example.composebytutorialv2.data.model.section3.PostType
import com.example.composebytutorialv2.ui.component.section3.chapter10.ImagePost
import com.example.composebytutorialv2.ui.component.section3.chapter10.TextPost
import com.example.composebytutorialv2.ui.component.section3.chapter12.JoinedToastView
import com.example.composebytutorialv2.ui.screen.section3.RedditViewModel
import java.util.*
import kotlin.concurrent.schedule

@Composable
fun HomeScreen(viewModel: RedditViewModel) {
    val posts: List<PostModel> by viewModel.allPosts.collectAsState(emptyList())
    var isToastVisible: Boolean by remember { mutableStateOf(false) }
    val onJoinClickAction: (Boolean) -> Unit = {
        isToastVisible = it
        if (isToastVisible) {
            Timer().schedule(3000) {
                isToastVisible = false
            }
        }
    }
    
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(Modifier.background(color = MaterialTheme.colors.secondary)) {
            items(posts) {
                if (it.type == PostType.TEXT) TextPost(it, onJoinClickAction)
                else ImagePost(it, onJoinClickAction)
                Spacer(Modifier.height(6.dp))
            }
        }
        
        Box(modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = 16.dp)
        ) {
            JoinedToastView(visible = isToastVisible)
        }
    }
}