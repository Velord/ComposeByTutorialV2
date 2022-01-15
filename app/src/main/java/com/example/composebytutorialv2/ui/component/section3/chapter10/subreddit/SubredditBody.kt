package com.example.composebytutorialv2.ui.component.section3.chapter10.subreddit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.composebytutorialv2.data.model.section3.SubredditModel

@Composable
fun SubredditBodyView(subredditModel: SubredditModel, modifier: Modifier = Modifier) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.surface)
    ) {
        val (backImage, icon, name, members, description) = createRefs()

        SubredditImageView(
            modifier.constrainAs(backImage) {
                centerHorizontallyTo(parent)
                top.linkTo(parent.top)
            }
        )

        SubredditIconView(
            modifier.constrainAs(icon) {
                top.linkTo(backImage.bottom)
                bottom.linkTo(backImage.bottom)
                centerHorizontallyTo(parent)
            }.zIndex(1f)
        )

        SubredditNameView(
            nameStringRes = subredditModel.nameStringRes,
            modifier = modifier.constrainAs(name) {
                top.linkTo(icon.bottom)
                centerHorizontallyTo(parent)
            }
        )

        SubredditMembersView(
            membersStringRes = subredditModel.membersStringRes,
            modifier = modifier.constrainAs(members) {
                top.linkTo(name.bottom)
                centerHorizontallyTo(parent)
            }
        )

        SubredditDescriptionView(
            descriptionStringRes = subredditModel.descriptionStringRes,
            modifier = modifier.constrainAs(description) {
                top.linkTo(members.bottom)
                centerHorizontallyTo(parent)
            }
        )
    }
}

@Preview
@Composable
fun SubredditBodyPreview() {
    SubredditBodyView(SubredditModel.DEFAULT_SUBREDDIT)
}