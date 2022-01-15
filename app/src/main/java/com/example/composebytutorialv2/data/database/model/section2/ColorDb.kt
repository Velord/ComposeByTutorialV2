package com.example.composebytutorialv2.data.database.model.section2

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ColorDb(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "hex") val hex: String,
    @ColumnInfo(name = "name") val name: String
) {

    companion object {

        val DEFAULT_COLORS = listOf(
            ColorDb(1, "#FFFFFF", "White"),
            ColorDb(2, "#E57373", "Red"),
            ColorDb(3, "#F06292", "Pink"),
            ColorDb(4, "#CE93D8", "Purple"),
            ColorDb(5, "#2196F3", "Blue"),
            ColorDb(6, "#00ACC1", "Cyan"),
            ColorDb(7, "#26A69A", "Teal"),
            ColorDb(8, "#4CAF50", "Green"),
            ColorDb(9, "#8BC34A", "Light Green"),
            ColorDb(10, "#CDDC39", "Lime"),
            ColorDb(11, "#FFEB3B", "Yellow"),
            ColorDb(12, "#FF9800", "Orange"),
            ColorDb(13, "#BCAAA4", "Brown"),
            ColorDb(14, "#9E9E9E", "Gray")
        )

        val DEFAULT_COLOR = DEFAULT_COLORS[0]
    }
}