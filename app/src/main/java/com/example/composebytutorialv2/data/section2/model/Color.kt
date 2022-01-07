package com.example.composebytutorialv2.data.section2.model

import com.example.composebytutorialv2.data.section2.database.model.ColorDb

data class Color(
    val id: Long,
    val name: String,
    val hex: String
) {

    companion object {

        val DEFAULT = with(ColorDb.DEFAULT_COLOR) { Color(id, name, hex) }
    }
}