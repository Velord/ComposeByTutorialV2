package com.example.composebytutorialv2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.composebytutorialv2.ui.chapter1.Greeting
import com.example.composebytutorialv2.ui.theme.ComposeByTutorialV2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DefaultPreview()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeByTutorialV2Theme {
        Surface(color = MaterialTheme.colors.background) {
            Greeting("Compose is a Shit")
        }
    }
}

