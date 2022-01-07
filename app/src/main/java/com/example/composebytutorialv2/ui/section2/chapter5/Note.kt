package com.example.composebytutorialv2.ui.section2.chapter5

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebytutorialv2.R
import com.example.composebytutorialv2.ui.section2.theme.rwGreen

@Composable
fun Note() {
    Row(Modifier.fillMaxWidth()) {
        Box(
            Modifier
                .size(40.dp)
                .background(rwGreen))
        Column(Modifier.weight(1f)) {
            Text(text = stringResource(id = R.string.title), maxLines = 1)
            Text(text = stringResource(id = R.string.content), maxLines = 1)
        }
        Checkbox(checked = false, onCheckedChange = {}, modifier = Modifier.padding(8.dp))
    }
}

@Preview
@Composable
private fun NotePreview() {
    Note()
}