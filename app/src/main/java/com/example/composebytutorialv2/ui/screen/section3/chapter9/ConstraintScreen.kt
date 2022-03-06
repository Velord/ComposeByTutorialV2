package com.example.composebytutorialv2.ui.screen.section3.chapter9

import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.composebytutorialv2.R

@Preview
@Composable
fun ConstraintScreen() {
    ConstraintLayout {
        val (passwordInput, eyeIcon) = createRefs()
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.baseline_arrow_circle_down_red_100_24dp),
            contentDescription = stringResource(id = R.string.eye),
            modifier = Modifier.constrainAs(eyeIcon){}
        )
    }
}