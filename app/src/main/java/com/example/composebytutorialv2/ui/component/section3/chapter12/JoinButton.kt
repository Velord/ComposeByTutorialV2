package com.example.composebytutorialv2.ui.component.section3.chapter12

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composebytutorialv2.R

enum class JoinButtonState {
    Idle, Pressed
}

@Composable
fun JoinButtonView(onClick: (Boolean) -> Unit = {}) {
    var buttonState by remember {
        mutableStateOf(JoinButtonState.Idle)
    }
    val shape = RoundedCornerShape(CornerSize(12.dp))
    val transition = updateTransition(
        targetState = buttonState,
        label = "JoinButtonTransition"
    )
    val duration = 3000
    val buttonBackColor by transition.animateColor(
        transitionSpec = { tween(duration) },
        label = "JoinButtonTransition Color"
    ) {
        if (it == JoinButtonState.Pressed) Color.White
        else Color.Blue
    }
    val buttonWidthMin by transition.animateDp(
        transitionSpec = { tween(duration) },
        label = "JoinButtonTransition Width Min"
    ) {
        if (it == JoinButtonState.Pressed) 32.dp else 70.dp
    }
    val textMaxWidth by transition.animateDp(
        transitionSpec = { tween(duration) },
        label = "JoinButtonTransition Text Max Width"
    ) {
        if (it == JoinButtonState.Pressed) 0.dp else 40.dp
    }
    val iconAsset =
        if (buttonState == JoinButtonState.Pressed) Icons.Default.Check
        else Icons.Default.Add
    val iconTintColor by transition.animateColor(
        transitionSpec = { tween(duration) },
        label = "JoinButtonTransition Icon Tint Color"
    ) {
        if (it == JoinButtonState.Pressed) Color.Blue
        else Color.White
    }

    Box(
        modifier = Modifier
            .clip(shape)
            .border(width = 1.dp, color = Color.Blue, shape = shape)
            .widthIn(buttonWidthMin)
            .background(buttonBackColor)
            .clickable {
                buttonState = if (buttonState == JoinButtonState.Idle) {
                    onClick(true)
                    JoinButtonState.Pressed
                } else {
                    onClick(false)
                    JoinButtonState.Idle
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = iconAsset,
                contentDescription = stringResource(R.string.plus_icon),
                tint = iconTintColor,
                modifier = Modifier.sizeIn(16.dp)
                    .padding(vertical = 2.dp, horizontal = 4.dp)
            )
            if (buttonState == JoinButtonState.Idle) {
                Text(
                    text = stringResource(R.string.join),
                    color = Color.White,
                    fontSize = 14.sp,
                    maxLines = 10,
                    modifier = Modifier.widthIn(min = 0.dp, max = textMaxWidth)
                        .padding(end = 8.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun JoinButtonPreview() {
    JoinButtonView {}
}