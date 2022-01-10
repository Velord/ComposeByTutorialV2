package com.example.composebytutorialv2.ui.section2.chapter6

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun NoteColorView(
    modifier: Modifier = Modifier,
    size: Dp,
    color: Color,
    borderSize: Dp
) {
    Box(
        modifier.size(size)
            .clip(CircleShape)
            .background(color)
            .border(
                border = BorderStroke(borderSize, SolidColor(Color.Black)),
                shape = CircleShape
            )
    )
}

@Preview
@Composable
fun NoteColorPreview() {
    NoteColorView(
        color = Color.Red,
        size = 40.dp,
        borderSize = 2.dp
    )
}