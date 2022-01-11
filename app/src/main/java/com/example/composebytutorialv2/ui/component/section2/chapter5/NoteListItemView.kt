package com.example.composebytutorialv2.ui.screen.section2.chapter5

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import com.example.composebytutorialv2.data.model.section2.NoteModel
import com.example.composebytutorialv2.ui.component.section2.chapter6.NoteColorView

@Composable
fun NoteListItemViewNotMaterial(
    note: NoteModel,
    onNoteClick: (NoteModel) -> Unit = {},
    onNoteCheckedChange: (NoteModel) -> Unit = {}
) {
    val backgroundShape = RoundedCornerShape(4.dp)

    Row(
        Modifier
            .padding(8.dp)
            .shadow(4.dp, backgroundShape)
            .fillMaxWidth()
            .heightIn(min = 64.dp)
            .background(Color.White, backgroundShape)
            .clickable { onNoteClick(note) }
    ) {
        NoteColorView(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(horizontal = 16.dp),
            size = 40.dp,
            color = note.color.getGraphicColor(),
            borderSize = 1.dp
        )

        Column(
            Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)) {
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

        //LOL я арирую
        //return does not work properly(always)
        if (note.isCheckedOff != null) {
            Checkbox(
                checked = note.isCheckedOff,
                onCheckedChange = {
                    val newNote = note.copy(isCheckedOff = it)
                    onNoteCheckedChange(newNote)
                },
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun NoteListItemView(
    modifier: Modifier = Modifier,
    note: NoteModel,
    onNoteClick: (NoteModel) -> Unit = {},
    onNoteCheckedChange: (NoteModel) -> Unit = {},
    isSelected: Boolean = false
) {
    val background = if (isSelected) Color.LightGray else MaterialTheme.colors.surface
    Card(
        shape = RoundedCornerShape(4.dp),
        modifier = modifier.padding(8.dp).fillMaxWidth(),
        backgroundColor = background
    ) {
        ListItem(
            text = { Text(text = note.title, maxLines = 1) },
            secondaryText = { Text(text = note.content, maxLines = 1) },
            icon = {
                NoteColorView(
                    size = 40.dp,
                    color = note.color.getGraphicColor(),
                    borderSize = 1.dp
                )
            },
            trailing = {
                //LOL я арирую
                //return does not work properly(always)
                if (note.isCheckedOff != null) {
                    Checkbox(
                        checked = note.isCheckedOff,
                        onCheckedChange = {
                            val newNote = note.copy(isCheckedOff = it)
                            onNoteCheckedChange(newNote)
                        },
                        modifier = Modifier.padding(8.dp)
                    )
                }
            },
            modifier = Modifier.clickable {
                onNoteClick(note)
            }
        )
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
private fun NoteListItemViewPreview() {
    NoteListItemView(note = NoteModel(1, "Note Title", "Note Content", null))
}