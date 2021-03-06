package com.example.composebytutorialv2.ui.component.section2.chapter6

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composebytutorialv2.data.model.section2.ColorModel

@Composable
fun ColorItemView(
    color: ColorModel,
    onColorSelect: (ColorModel) -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .clickable {
                onColorSelect(color)
            }
    ) {
        NoteColorView(
            modifier = Modifier.padding(10.dp),
            color = color.getGraphicColor(),
            size = 80.dp,
            borderSize = 2.dp
        )

        Text(
            text = color.name,
            fontSize = 22.sp,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.CenterVertically)
        )
    }
}

@Preview
@Composable
fun ColorItemPreview() {
    ColorItemView(ColorModel.DEFAULT) {}
}