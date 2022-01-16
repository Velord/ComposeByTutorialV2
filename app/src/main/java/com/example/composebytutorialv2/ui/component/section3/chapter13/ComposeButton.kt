package com.example.composebytutorialv2.ui.component.section3.chapter13

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composebytutorialv2.R

@Composable
fun ComposeButtonView(onButtonClick: () -> Unit) {
    val buttonColors = ButtonDefaults.buttonColors(
        backgroundColor = Color(0xff006837),
        contentColor = Color.White
    )

    Button(
        onClick = onButtonClick,
        elevation = null,
        shape = RoundedCornerShape(CornerSize(24.dp)),
        contentPadding = PaddingValues(start = 32.dp, end = 32.dp),
        colors = buttonColors,
        modifier = Modifier.height(48.dp)
    ) {
        Text(
            text = stringResource(id = R.string.start_chatting).uppercase(),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview
@Composable
private fun ComposeButtonPreview() {
    ComposeButtonView {}
}