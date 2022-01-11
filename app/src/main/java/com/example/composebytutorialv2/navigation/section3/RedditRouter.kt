package com.example.composebytutorialv2.ui.screen.section3

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.composebytutorialv2.R

sealed class RedditScreen(val titleResId: Int) {
    object Home : RedditScreen(R.string.home)
    object SubscriptionList : RedditScreen(R.string.subreddits)
    object NewPost : RedditScreen(R.string.new_post)
    object MyProfile : RedditScreen(R.string.my_profile)
}

object RedditRouter {

    var currentScreen: MutableState<RedditScreen> = mutableStateOf(
        RedditScreen.Home
    )

    private var previousScreen: MutableState<RedditScreen> = mutableStateOf(
        RedditScreen.Home
    )

    fun navigateTo(destination: RedditScreen) {
        previousScreen.value = currentScreen.value
        currentScreen.value = destination
    }

    fun goBack() {
        currentScreen.value = previousScreen.value
    }
}