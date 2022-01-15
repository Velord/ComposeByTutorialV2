package com.example.composebytutorialv2.ui.component.section3.chapter10.subreddit

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composebytutorialv2.data.model.section3.SubredditModel

@Composable
fun SubredditView(subredditModel: SubredditModel, modifier: Modifier = Modifier) {
    Card(
        backgroundColor = MaterialTheme.colors.surface,
        shape = RoundedCornerShape(4.dp),
        modifier = modifier
            .size(120.dp)
            .padding(horizontal = 2.dp, vertical = 4.dp)
    ) {
        SubredditBodyView(subredditModel)
    }
}