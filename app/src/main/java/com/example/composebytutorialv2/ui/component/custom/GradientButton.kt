package com.example.composebytutorialv2.ui.component.custom

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composebytutorialv2.R
import com.example.composebytutorialv2.ui.theme.InterFont
import com.example.composebytutorialv2.ui.theme.gradientHeliotropeToBrinkPinkToCoral
import java.util.*

@Composable
fun GradientButton(
    @StringRes textResId: Int,
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    gradient: Brush = gradientHeliotropeToBrinkPinkToCoral(),
    onClick: () -> Unit = {},
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 40.dp, vertical = 78.dp)
            .clip(RoundedCornerShape(160.dp))
            .background(gradient),
        elevation = null,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent, contentColor = Color.Transparent)
    ) {
        Text(
            text = stringResource(textResId).uppercase(Locale.getDefault()),
            modifier = textModifier.padding(vertical = 12.dp),
            color = Color.White,
            fontSize = 16.sp,
            fontFamily = InterFont,
            fontWeight = FontWeight.SemiBold,
            fontStyle = FontStyle.Normal,
        )
    }
}

@Preview
@Composable
fun GradientButtonPreview() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        GradientButton(textResId = R.string.change_theme)
    }
}