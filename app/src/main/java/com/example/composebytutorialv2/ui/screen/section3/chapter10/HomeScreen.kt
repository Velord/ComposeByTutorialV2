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
import com.example.composebytutorialv2.ui.component.section3.chapter13.TrendingTopicCollectionView
import com.example.composebytutorialv2.ui.component.section3.chapter13.trendingItems
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
    val homeScreenItems = mapHomeScreenItems(posts)
    
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(Modifier.background(color = MaterialTheme.colors.secondary)) {
            items(homeScreenItems) {
                when(it.type) {
                    HomeScreenItemType.Trending -> TrendingTopicCollectionView(
                        trendingTopics = trendingItems,
                        modifier = Modifier.padding(
                            top = 16.dp,
                            bottom = 6.dp
                        )
                    )
                    else -> {
                        it.post?.let { post ->
                            if (post.type == PostType.TEXT) TextPost(
                                post = post,
                                onJoinButtonClick = onJoinClickAction
                            )
                            else ImagePost(
                                post = post,
                                onJoinButtonClick = onJoinClickAction
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(6.dp))
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

private enum class HomeScreenItemType {
    Trending, Post
}

private data class HomeScreenItem(
    val type: HomeScreenItemType,
    val post: PostModel? = null
)

private fun mapHomeScreenItems(
    posts: List<PostModel>
): List<HomeScreenItem> {
    val homeScreenItems = mutableListOf<HomeScreenItem>()

    // Add Trending item
    homeScreenItems.add(
        HomeScreenItem(HomeScreenItemType.Trending)
    )

    // Add Post items
    posts.forEach { post ->
        homeScreenItems.add(
            HomeScreenItem(HomeScreenItemType.Post, post)
        )
    }

    return homeScreenItems
}