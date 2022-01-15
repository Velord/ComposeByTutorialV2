package com.example.composebytutorialv2.ui.component.section3.chapter10

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebytutorialv2.R

@Composable
fun ArrowButtonView(arrowResourceId: Int, onClick: () -> Unit) {
    IconButton(onClick = onClick, Modifier.size(30.dp)) {
        Icon(
            imageVector = ImageVector.vectorResource(id = arrowResourceId),
            contentDescription = stringResource(id = R.string.upvote),
            modifier = Modifier.size(20.dp),
            tint = Color.Gray
        )
    }
}

@Preview
@Composable
fun ArrowButtonPreview() {
    ArrowButtonView(R.drawable.baseline_arrow_circle_up_purple_50_48dp) {}
}