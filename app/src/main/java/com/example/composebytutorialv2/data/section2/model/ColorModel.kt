package com.example.composebytutorialv2.data.section2.model

import androidx.compose.ui.graphics.Color
import com.example.composebytutorialv2.data.section2.database.model.ColorDb

data class ColorModel(
    val id: Long,
    val name: String,
    val hex: String
) {

    companion object {
        val DEFAULT = with(ColorDb.DEFAULT_COLOR) { ColorModel(id, name, hex) }

        fun fromHex(hex: String): Color = Color(android.graphics.Color.parseColor(hex))
    }
}