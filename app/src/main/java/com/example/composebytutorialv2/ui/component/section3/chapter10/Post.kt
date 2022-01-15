package com.example.composebytutorialv2.ui.component.section3.chapter10

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebytutorialv2.R
import com.example.composebytutorialv2.data.model.section3.PostModel

@Composable
fun PostView(post: PostModel, content: @Composable () -> Unit = {}) {
    Card(shape = MaterialTheme.shapes.large) {
        Column(Modifier.padding(horizontal = 8.dp)) {
            HeaderView(post)
            Spacer(Modifier.height(4.dp))
            content()
            Spacer(Modifier.height(8.dp))
            PostActions(post)
        }
    }
}

@Preview
@Composable
fun PostPreview() {
    PostView(PostModel.DEFAULT_POST)
}

@Preview
@Composable
fun TextPostPreview() {
    PostView(PostModel.DEFAULT_POST) {
        TextContent(PostModel.DEFAULT_POST.text)
    }
}

@Preview
@Composable
fun ImagePostPreview() {
    PostView(PostModel.DEFAULT_POST) {
        ImageContent(PostModel.DEFAULT_POST.image ?: R.drawable.baseline_assignment_return_light_blue_400_48dp)
    }
}