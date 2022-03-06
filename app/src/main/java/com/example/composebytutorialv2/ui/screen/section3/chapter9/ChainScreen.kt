package com.example.composebytutorialv2.ui.screen.section3.chapter9

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.composebytutorialv2.R

@Preview
@Composable
private fun ChainScreen() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (first, second, third) = createRefs()

        Button(
            modifier = Modifier.constrainAs(first) {
                start.linkTo(parent.start)
                end.linkTo(second.start)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            },
            onClick = {},
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.baseline_account_balance_wallet_pink_a200_48dp),
                contentDescription = "",
                tint = Color.Blue
            )
        }

        Button(
            modifier = Modifier.constrainAs(second) {
                start.linkTo(first.end)
                end.linkTo(third.start)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            },
            onClick = {},
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.outline_gavel_red_500_24dp),
                contentDescription = "",
                tint = Color.Red
            )
        }

        Button(
            modifier = Modifier.constrainAs(third) {
                start.linkTo(second.end)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            },
            onClick = {},
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.baseline_account_balance_wallet_pink_a200_48dp),
                contentDescription = "",
                tint = Color.Green
            )
        }

        createHorizontalChain(first, second, third, chainStyle = ChainStyle.SpreadInside)
    }
}