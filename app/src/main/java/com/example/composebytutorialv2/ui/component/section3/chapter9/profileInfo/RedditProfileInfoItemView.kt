package com.example.composebytutorialv2.ui.component.section3.chapter9.profileInfo

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.composebytutorialv2.R

@Preview
@Composable
private fun RedditProfileInfoItemPreview() {
    RedditProfileInfoItemView(
        Modifier,
        Icons.Filled.ShoppingCart,
        R.string.age,
        R.string.default_age_amount,
    )
}

@Composable
fun RedditProfileInfoItemView(
    modifier: Modifier = Modifier,
    iconResId: ImageVector,
    @StringRes textResId: Int,
    @StringRes amountResId: Int,
) {
    val colors = MaterialTheme.colors

    ConstraintLayout(modifier = modifier) {
        val (iconRef, amountRef, titleRef) = createRefs()
        val itemModifier = Modifier

        Icon(
            contentDescription = stringResource(textResId),
            imageVector = iconResId,
            tint = Color.Blue,
            modifier = itemModifier
                .constrainAs(iconRef) {
                    centerVerticallyTo(parent)
                    start.linkTo(parent.start)
                }
                .padding(start = 16.dp)
        )

        Text(
            text = stringResource(amountResId),
            color = colors.primaryVariant,
            fontSize = 18.sp,
            modifier = itemModifier.padding(start = 8.dp)
                .constrainAs(amountRef) {
                    top.linkTo(iconRef.top)
                    start.linkTo(iconRef.end)
                    bottom.linkTo(titleRef.top)
                }
        )

        Text(
            text = stringResource(textResId),
            color = Color.Gray,
            fontSize = 10.sp,
            modifier = itemModifier.padding(start = 8.dp)
                .constrainAs(titleRef) {
                    top.linkTo(amountRef.bottom)
                    start.linkTo(iconRef.end)
                    bottom.linkTo(iconRef.bottom)
                }
        )
    }
}