package com.example.composebytutorialv2.ui.component.section3.chapter10

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.composebytutorialv2.R

@Composable
fun Voting3453453Action(
    text: String,
    onUpVoteAction: () -> Unit,
    onDownVoteAction: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        ArrowButtonView(R.drawable.baseline_arrow_circle_down_red_100_24dp, onUpVoteAction)
        Text(
            text = text,
            color = Color.Gray,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp
        )
        ArrowButtonView(R.drawable.baseline_arrow_circle_up_purple_50_48dp, onDownVoteAction)
    }
}

@Composable
fun VotingActionView(
    text: String,
    upVoteAction: () -> Unit,
    downVoteAction: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        ArrowButtonView(
            arrowResourceId = R.drawable.baseline_arrow_circle_up_purple_50_48dp,
            onClick = upVoteAction
        )
        Text(
            text = text,
            color = Color.Gray,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp
        )
        ArrowButtonView(
            arrowResourceId = R.drawable.baseline_arrow_circle_down_red_100_24dp,
            onClick = downVoteAction
        )
    }
}

@Preview
@Composable
fun VotingActionPreview() {
    VotingActionView("555", {}, {})
}