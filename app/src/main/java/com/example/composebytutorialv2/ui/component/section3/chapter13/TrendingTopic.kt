package com.example.composebytutorialv2.ui.component.section3.chapter13

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebytutorialv2.R

data class TrendingTopicModel(
    val text: String,
    @DrawableRes val imageRes: Int = 0
)

val trendingItems = listOf(
    TrendingTopicModel(
        "Compose Tutorial",
        R.drawable.baseline_assignment_return_light_blue_400_48dp
    ),
    TrendingTopicModel(
        "Compose Animations",
        R.drawable.round_analytics_blue_600_48dp
    ),
    TrendingTopicModel(
        "Compose Migration",
        R.drawable.ic_launcher_foreground
    ),
    TrendingTopicModel(
        "DataStore Tutorial",
        R.drawable.baseline_assignment_late_purple_300_48dp
    ),
    TrendingTopicModel(
        "Android Animations",
        R.drawable.baseline_assignment_ind_red_100_24dp
    ),
    TrendingTopicModel(
        "Deep Links in Android",
        R.drawable.baseline_arrow_right_alt_deep_purple_800_48dp
    )
)

//WTF ?????
@Composable
private fun TrendingTopicView(trendingTopic: TrendingTopicModel) {
//    AndroidView(factory =  { context ->
//        TrendingTopicView(context)
//    }
//    )
}

@Composable
fun TrendingTopicCollectionView(
    trendingTopics: List<TrendingTopicModel>,
    modifier: Modifier = Modifier
) {
    Card(
        shape = MaterialTheme.shapes.large,
        modifier = modifier
    ) {
        Column(modifier = Modifier.padding(vertical = 8.dp)) {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(18.dp),
                    imageVector = Icons.Filled.Star,
                    tint = Color.Blue,
                    contentDescription = stringResource(id = R.string.star_icon)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stringResource(id = R.string.trending_today),
                    fontWeight = FontWeight.Bold,
                    color =Color.Black
                )
            }

            Spacer(Modifier.height(8.dp))

            LazyRow(
                contentPadding = PaddingValues(
                    top = 8.dp,
                    start = 16.dp,
                    end = 16.dp
                ),
                content = {
                    itemsIndexed(
                        items = trendingTopics,
                        itemContent = { index, trendingModel ->
                            TrendingTopicView(trendingModel)
                            if (index != trendingTopics.lastIndex) {
                                Spacer(Modifier.width(8.dp))
                            }
                        }
                    )
                }
            )
        }
    }
}

@Preview
@Composable
private fun TrendingTopicCollectionPreview() {
    TrendingTopicCollectionView(trendingTopics = trendingItems)
}