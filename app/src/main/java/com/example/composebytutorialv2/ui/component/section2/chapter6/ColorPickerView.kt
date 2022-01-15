package com.example.composebytutorialv2.ui.component.section2.chapter6

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composebytutorialv2.R
import com.example.composebytutorialv2.data.model.section2.ColorModel

@Composable
fun ColorPickerView(
    colors: List<ColorModel>,
    onColorSelect: (ColorModel) -> Unit
) {
    Column(Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(id = R.string.color_picker),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )

        LazyColumn(Modifier.fillMaxWidth()) {
            items(colors) {
                ColorItemView(color = it, onColorSelect = onColorSelect)
            }
        }
    }
}

@Preview
@Composable
fun ColorPickerPreview() {
    ColorPickerView(
        colors = listOf(ColorModel.DEFAULT, ColorModel.DEFAULT, ColorModel.DEFAULT),
        onColorSelect = {}
    )
}