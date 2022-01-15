package com.example.composebytutorialv2.ui.screen.section1.chapter4

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.composebytutorialv2.R
import com.example.composebytutorialv2.navigation.Router
import com.example.composebytutorialv2.navigation.Screen
import com.example.composebytutorialv2.navigation.section1And2.BackButtonHandler
import com.example.composebytutorialv2.ui.component.section1.chapter4.GridView
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode

data class IconResource(
    val id: Int = -1,
    @DrawableRes val resId: Int = R.drawable.outline_assignment_turned_in_orange_300_48dp,
    val isVisible: Boolean = true
)

val icons = listOf(
    IconResource(0, R.drawable.round_accessible_deep_purple_a200_48dp),
    IconResource(1, R.drawable.round_accessibility_red_400_48dp),
    IconResource(2, R.drawable.baseline_accessibility_new_black_48dp),
    IconResource(3, R.drawable.baseline_arrow_circle_down_red_100_24dp),
    IconResource(4, R.drawable.baseline_arrow_circle_up_purple_50_48dp),
    IconResource(5, R.drawable.baseline_arrow_right_alt_deep_purple_800_48dp),
    IconResource(6, R.drawable.baseline_account_balance_red_100_48dp),
    IconResource(7, R.drawable.baseline_account_balance_wallet_pink_a200_48dp),
    IconResource(8, R.drawable.baseline_account_box_blue_a200_48dp),
    IconResource(9, R.drawable.baseline_assignment_return_light_blue_400_48dp),
)

@Preview
@Composable
fun GridScreen() {
    LazyColumn(Modifier.fillMaxWidth()) {
        val columnCount = 5
        item {
            GridView(
                modifier = Modifier.wrapContentHeight(),
                content = icons,
                columnCount = columnCount,
                useFlow = true,
                weight = {
                    if (it.id in 0..2) 1f else 3f
                },
                cell = {
                    GridIcon(it)
                }
            )
        }

        //can't be scroll inside scroll
//        item {
//            LazyVerticalGrid(
//                modifier = Modifier.height(100.dp)
//                    .padding(vertical = 32.dp),
//                cells = GridCells.Adaptive(170.dp),
//                content = {
//                    items(icons) {
//                        GridIcon(it)
//                    }
//                }
//            )
//        }

        item {
            val itemSize: Dp = LocalConfiguration.current.screenWidthDp.dp / 2

            FlowRow(
                mainAxisSize = SizeMode.Expand,
                mainAxisAlignment = FlowMainAxisAlignment.SpaceEvenly
            ) {
                icons.forEach {
                    GridIcon(it, Modifier.size(itemSize))
                }
            }
        }
    }

    BackButtonHandler {
        Router.navigateTo(Screen.Section1.Navigation)
    }
}

@Composable
fun GridIcon(icon: IconResource, modifier: Modifier = Modifier) {
    Surface(
        border = BorderStroke(2.dp, Color.Red),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 4.dp)
    ) {
        Icon(
            painter = painterResource(icon.resId),
            contentDescription = stringResource(id = R.string.grid_icon),
            modifier = Modifier.size(80.dp),
            tint = Color.Green
        )
    }
}