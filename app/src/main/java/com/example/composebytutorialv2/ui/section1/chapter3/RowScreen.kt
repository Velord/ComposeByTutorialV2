package com.example.composebytutorialv2.section1.chapter3

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.composebytutorialv2.R

val THREE_EMPTY_LIST = listOf(
    R.string.first,
    R.string.second,
    R.string.third
)

@Preview
@Composable
fun RowScreen() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
        ) {
        THREE_EMPTY_LIST.forEach {
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(id = it),
                fontSize = 18.sp,
                color = Color.Green
            )
        }
    }
}