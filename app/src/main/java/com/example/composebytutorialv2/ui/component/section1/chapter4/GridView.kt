package com.example.composebytutorialv2.ui.component.section1.chapter4

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode

data class CellItem<T>(
    val value: T? = null,
    val isVisible: Boolean = false,
    val weight: Float = 1f
)

data class RowItem<T>(
    val items: List<CellItem<T>>,
    val weight: Float
)

inline fun <T> buildGrid(
    items: List<T>,
    columnCount: Int,
    crossinline weightF: (T) -> Float,
): List<List<CellItem<T>>> {
    val gridCollection = mutableListOf<RowItem<T>>()
    val maxRowWeight = 1f * columnCount

    var rowCollection = mutableListOf<CellItem<T>>()
    var rowWeight = 0f
    var itemsCountInARow = 0
    items.forEach {
        val weight = weightF(it)
        if (weight > maxRowWeight)
            error("Weight can not be greater than column count")

        val weightIsOver = weight + rowWeight > maxRowWeight
        if (weightIsOver || itemsCountInARow == columnCount) {
            gridCollection += RowItem(rowCollection, rowWeight)
            rowCollection = mutableListOf()
            rowWeight = 0f
            itemsCountInARow = 0
        }

        rowCollection += CellItem(value = it, isVisible = true, weight = weight)
        ++itemsCountInARow
        rowWeight += weight
    }


    val grids = gridCollection.map { row ->
        val rowItems = mutableListOf(*row.items.toTypedArray())
        val itemsToFill = columnCount - row.weight
        (0 until itemsToFill.toInt()).forEach { _ ->
            rowItems += CellItem()
        }

        rowItems
    }

    return grids
}

@Composable
inline fun <T> GridView(
    modifier: Modifier = Modifier,
    content: List<T>,
    columnCount: Int,
    useFlow: Boolean = false,
    crossinline blankContent: @Composable RowScope.() -> Unit = { BlankCell() },
    crossinline weight: (T) -> Float = { 1f },
    crossinline cell: @Composable (T) -> Unit
) {
    val gridItems = buildGrid(content, columnCount, weight)

    if (useFlow) {
        FlowRow(
            mainAxisSize = SizeMode.Expand,
            mainAxisAlignment = FlowMainAxisAlignment.SpaceEvenly
        ) {
            gridItems.forEach {
                RowItem(it, blankContent = blankContent, content = cell)
            }
        }
    } else {
        LazyColumn(modifier) {
            items(gridItems) {
                RowItem(it, blankContent = blankContent, content = cell)
            }
        }
    }
}

@Composable
inline fun <T> RowItem(
    rowItems: List<CellItem<T>>,
    crossinline blankContent: @Composable RowScope.() -> Unit,
    crossinline content: @Composable (T) -> Unit
) {
    Row {
        rowItems.forEach {
            CellItem(item = it, blankContent = blankContent, content = content)
        }
    }
}

@Composable
inline fun <T> RowScope.CellItem(
    item: CellItem<T>,
    crossinline blankContent: @Composable RowScope.() -> Unit,
    crossinline content: @Composable (T) -> Unit
) {
    if (item.value == null) blankContent()
    else Surface(
        modifier = Modifier.weight(item.weight),
        content = {
            content(item.value)
        }
    )
}

@Composable
fun RowScope.BlankCell() {
    Surface(
        color = Color.Transparent,
        modifier = Modifier.weight(1f),
        content = {}
    )
}