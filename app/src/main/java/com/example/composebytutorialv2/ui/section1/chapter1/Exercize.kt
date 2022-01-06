package com.example.composebytutorialv2.ui.section1.chapter1

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.composebytutorialv2.R

@Preview
@Composable
fun Greeting(name: String = "") {
    Text("Hello $name!")
}


@Composable
fun TextButton(text: String) {
    Box(Modifier.clickable {  }) {
        Text(text = text)
    }
}

@Composable
fun ImageButton() {
    Box(modifier = Modifier.clickable {  }) {
        Icon(painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "")
    }
}

@Composable
fun TextImageButton(text: String) {
    Box(modifier = Modifier.clickable {  }) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = ""
            )
            Text(text = "TextImageButton")
        }
    }
}

data class PostData(val id: String, @DrawableRes val image: Int, val title: String)

@Composable
fun Post(post: PostData, onClickAction: () -> Unit) {
    Box(modifier = Modifier.clickable(onClick = onClickAction)) {
        Row {
            Icon(
                painterResource(id = post.image),
                contentDescription = ""
            )
            Text(text = post.title)
        }
    }
}