package com.example.composebytutorialv2.ui.screen.section3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composebytutorialv2.data.model.section3.PostModel
import com.example.composebytutorialv2.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RedditViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val allPosts by lazy { repository.getAllPosts() }

    val myPosts by lazy { repository.getAllOwnedPosts() }

    val subreddits by lazy { MutableStateFlow<List<String>>(emptyList()) }

    val selectedCommunity by lazy { MutableStateFlow("") }

    fun selectCommunity(community: String) {
        selectedCommunity.value = community
    }

    fun searchCommunities(searchedText: String) {
        viewModelScope.launch(Dispatchers.Default) {
            subreddits.update {
                (repository.getAllSubreddits(searchedText))
            }
        }
    }

    fun savePost(post: PostModel) {
        viewModelScope.launch(Dispatchers.Default) {
            repository.insertPost(post.copy(subreddit = selectedCommunity.value))
        }
    }
}