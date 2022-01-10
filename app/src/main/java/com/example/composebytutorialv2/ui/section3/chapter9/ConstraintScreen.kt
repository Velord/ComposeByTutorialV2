package com.example.composebytutorialv2.ui.section3.chapter9

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
            imageVector = ImageVector.vectorResource(id = R.drawable.outline_remove_red_eye_red_700_48dp),
            contentDescription = stringResource(id = R.string.eye),
            modifier = Modifier.constrainAs(eyeIcon){}
        )
    }
}