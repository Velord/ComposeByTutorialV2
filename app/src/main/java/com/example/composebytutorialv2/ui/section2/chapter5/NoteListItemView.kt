package com.example.composebytutorialv2.ui.section2.chapter5

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composebytutorialv2.data.section2.model.ColorModel
import com.example.composebytutorialv2.data.section2.model.NoteModel
import com.example.composebytutorialv2.ui.section2.chapter6.NoteColor

@Composable
fun NoteListItemView(
    note: NoteModel,
    onNoteClick: (NoteModel) -> Unit = {},
    onNoteCheckedChange: (NoteModel) -> Unit = {}
) {
    val backgroundShape = RoundedCornerShape(4.dp)

    Row(
        Modifier.padding(8.dp)
            .shadow(4.dp, backgroundShape)
            .fillMaxWidth()
            .heightIn(min = 64.dp)
            .background(Color.White, backgroundShape)
            .clickable { onNoteClick(note) }
    ) {
        NoteColor(
            modifier = Modifier.align(Alignment.CenterVertically)
                .padding(horizontal = 16.dp),
            size = 40.dp,
            color = ColorModel.fromHex(note.color.hex),
            borderSize = 1.dp
        )

        Column(Modifier.weight(1f).align(Alignment.CenterVertically)) {
            Text(
                text = note.title,
                maxLines = 1,
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    letterSpacing = 0.15.sp
                )
            )
            Text(
                text = note.content,
                color = Color.Black.copy(alpha = 0.75f),
                maxLines = 1,
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    letterSpacing = 0.25.sp
                )
            )
        }

        //LOL
        if (note.isCheckedOff != null) {
            Checkbox(
                checked = note.isCheckedOff,
                onCheckedChange = {
                    val newNote = note.copy(isCheckedOff = it)
                    onNoteCheckedChange(newNote)
                },
                modifier = Modifier.padding(16.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}

@Preview
@Composable
private fun NoteListItemViewPreview() {
    NoteListItemView(NoteModel(1, "Note Title", "Note Content", null))
}