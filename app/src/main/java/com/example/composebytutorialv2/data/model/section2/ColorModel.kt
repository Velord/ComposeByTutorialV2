package com.example.composebytutorialv2.data.model.section2

import androidx.compose.ui.graphics.Color
import com.example.composebytutorialv2.data.database.model.section2.ColorDb

data class ColorModel(
    val id: Long,
    val name: String,
    val hex: String
) {

    fun getGraphicColor(): Color = Color(android.graphics.Color.parseColor(hex))

    companion object {
        val DEFAULT = with(ColorDb.DEFAULT_COLOR) { ColorModel(id, name, hex) }
    }
}