package com.example.composebytutorialv2.ui.component.section3.chapter10

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composebytutorialv2.R
import java.util.*

@Composable
fun BackgroundTextView(text: String) {
    Text(
        fontWeight = FontWeight.Medium,
        text = text.uppercase(Locale.getDefault()),
        fontSize = 10.sp,
        color = Color.Red,
        modifier = Modifier
            .background(color = MaterialTheme.colors.secondary)
            .padding(start = 16.dp, top = 4.dp, bottom = 4.dp)
            .fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}

@Preview
@Composable
fun BackgroundTextPreview() {
    BackgroundTextView(stringResource(id = R.string.recently_visited_subreddits))
}