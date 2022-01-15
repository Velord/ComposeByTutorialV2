package com.example.composebytutorialv2.data.model.section3

import com.example.composebytutorialv2.R

data class PostModel(
    val username: String,
    val subreddit: String,
    val title: String,
    val text: String,
    val likes: String,
    val comments: String,
    val type: PostType,
    val postedTime: String,
    val image: Int?
) {

    companion object {

        val DEFAULT_POST = PostModel(
            "raywenderlich",
            "androiddev",
            "Watch this awesome Jetpack Compose course!",
            "",
            "5614",
            "523",
            PostType.IMAGE,
            "4h",
            R.drawable.baseline_arrow_right_alt_deep_purple_800_48dp
        )
    }
}