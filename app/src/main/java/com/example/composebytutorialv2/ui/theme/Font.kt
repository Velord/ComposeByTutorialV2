package com.example.composebytutorialv2.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.composebytutorialv2.R

val InterFont = FontFamily(
    Font(R.font.inter),
    Font(R.font.inter_bold, weight = FontWeight.Bold),
    Font(R.font.inter_light, weight = FontWeight.Light),
    Font(R.font.inter_semibold, weight = FontWeight.SemiBold),
    Font(R.font.inter, weight = FontWeight.Normal, style = FontStyle.Italic),
)

val LoraFont = FontFamily(
    Font(R.font.lora),
    Font(R.font.lora_bold, weight = FontWeight.Bold),
    Font(R.font.lora_medium, weight = FontWeight.Medium),
    Font(R.font.lora, weight = FontWeight.Normal, style = FontStyle.Italic),
)

