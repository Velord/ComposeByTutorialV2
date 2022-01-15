package com.example.composebytutorialv2.ui.component.section3.chapter9.profileInfo

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.composebytutorialv2.R

@Composable
fun RedditProfileInfoView(modifier: Modifier = Modifier) {
    ConstraintLayout(
        modifier
            .fillMaxWidth()
            .padding(16.dp)) {
        val (karmaItem, divider, ageItem) = createRefs()
        val colors = MaterialTheme.colors

        RedditProfileInfoItemView(
            iconResId = Icons.Filled.Star,
            textResId = R.string.karma,
            amountResId = R.string.default_karma_amount,
            modifier = modifier.constrainAs(karmaItem) {
                centerVerticallyTo(parent)
                start.linkTo(parent.start)
            }
        )

        Divider(
            modifier = modifier.width(1.dp)
                .constrainAs(divider) {
                    centerVerticallyTo(karmaItem)
                    centerHorizontallyTo(parent)
                    height = Dimension.fillToConstraints
                },
            color = colors.onSurface.copy(.2f)
        )

        RedditProfileInfoItemView(
            iconResId = Icons.Filled.ShoppingCart,
            textResId = R.string.age,
            amountResId = R.string.default_age_amount,
            modifier = modifier.constrainAs(ageItem) {
                start.linkTo(divider.end)
                centerVerticallyTo(parent)
            }
        )
    }
}