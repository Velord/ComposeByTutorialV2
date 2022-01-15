package com.example.composebytutorialv2.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import com.example.composebytutorialv2.R

@Composable
fun gradientHeliotropeToBrinkPinkToCoral(): Brush = Brush.horizontalGradient(
    colors = listOf(
        colorResource(id = R.color.heliotrope_gradient),
        colorResource(id = R.color.brink_pink),
        colorResource(id = R.color.coral),
    )
)