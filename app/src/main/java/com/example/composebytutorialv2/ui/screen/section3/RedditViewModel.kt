package com.example.composebytutorialv2.ui.screen.section3

import androidx.lifecycle.ViewModel
import com.example.composebytutorialv2.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RedditViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val allPosts by lazy { repository.getAllPosts() }

    val myPosts by lazy { repository.getAllOwnedPosts() }
}