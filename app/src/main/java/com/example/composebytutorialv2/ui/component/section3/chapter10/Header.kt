package com.example.composebytutorialv2.ui.component.section3.chapter10

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composebytutorialv2.R
import com.example.composebytutorialv2.data.model.section3.PostModel
import com.example.composebytutorialv2.data.model.section3.PostModel.Companion.DEFAULT_POST
import com.example.composebytutorialv2.ui.component.section3.chapter12.JoinButtonView

@Composable
fun TextPost(
    post: PostModel,
    onJoinButtonClick: (Boolean) -> Unit = {}
) {
    PostView(post, onJoinButtonClick) {
        TextContent(post.text)
    }
}

@Composable
fun ImagePost(
    post: PostModel,
    onJoinButtonClick: (Boolean) -> Unit = {}
) {
    PostView(post, onJoinButtonClick) {
        ImageContent(post.image ?: R.drawable.baseline_assignment_late_purple_300_48dp)
    }
}

@Composable
fun HeaderView(
    post: PostModel,
    onJoinButtonClick: (Boolean) -> Unit = {}
) {
    Row(
        modifier = Modifier.padding(start = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            ImageVector.vectorResource(id = R.drawable.sharp_home_blue_600_48dp),
            contentDescription = stringResource(id = R.string.subreddits),
            Modifier
                .size(40.dp)
                .clip(CircleShape)
        )
        Spacer(Modifier.width(8.dp))
        Column(Modifier.weight(1f)) {
            Text(
                text = stringResource(id = R.string.subreddits, post.subreddit),
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colors.primaryVariant
            )
            Text(
                text = stringResource(id = R.string.post_header, post.username, post.postedTime),
                fontWeight = FontWeight.Medium,
                color = Color.Gray
            )
        }
        Spacer(Modifier.width(4.dp))
        JoinButtonView(onJoinButtonClick)
        MoreActionsMenu()
    }
    Title(text = post.title)
}

@Composable
fun MoreActionsMenu() {
    var expanded by remember { mutableStateOf(false) }
    Box(modifier = Modifier.wrapContentSize(Alignment.TopStart)) {

        IconButton(onClick = { expanded = true }) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                tint = Color.DarkGray,
                contentDescription = stringResource(id = R.string.more_actions)
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            CustomDropdownMenuItem(
                vectorResourceId = R.drawable.outline_remove_red_eye_red_700_48dp,
                text = stringResource(id = R.string.save)
            )
        }
    }
}

@Composable
fun CustomDropdownMenuItem(
    @DrawableRes vectorResourceId: Int,
    color: Color = Color.Black,
    text: String,
    onClickAction: () -> Unit = {}
) {
    DropdownMenuItem(onClick = onClickAction) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = ImageVector.vectorResource(id = vectorResourceId),
                tint = color,
                contentDescription = stringResource(id = R.string.save)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = text, fontWeight = FontWeight.Medium, color = color)
        }
    }
}

@Composable
fun Title(text: String) {
    Text(
        text = text,
        maxLines = 3,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        color = MaterialTheme.colors.primaryVariant,
        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
    )
}

@Composable
fun TextContent(text: String) {
    Text(
        modifier = Modifier.padding(
            start = 16.dp,
            end = 16.dp
        ),
        text = text,
        color = Color.Gray,
        fontSize = 12.sp,
        maxLines = 3
    )
}

@Composable
fun ImageContent(vectorResourceId: Int) {
    val imageAsset = ImageVector.vectorResource(id = vectorResourceId)
    Image(
        imageVector = imageAsset,
        contentDescription = stringResource(id = R.string.post_header_description),
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1F),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun PostActions(post: PostModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        VotingActionView(text = post.likes, upVoteAction = {}, downVoteAction = {})
        PostAction(
            vectorResourceId = R.drawable.baseline_comment_bank_red_300_48dp,
            text = post.comments,
            onClickAction = {}
        )
        PostAction(
            vectorResourceId = R.drawable.baseline_mobile_screen_share_blue_400_48dp,
            text = stringResource(R.string.share),
            onClickAction = {}
        )
        PostAction(
            vectorResourceId = R.drawable.baseline_emoji_events_cyan_50_48dp,
            text = stringResource(R.string.award),
            onClickAction = {}
        )
    }
}

@Composable
fun PostAction(
    @DrawableRes vectorResourceId: Int,
    text: String,
    onClickAction: () -> Unit
) {
    Box(modifier = Modifier.clickable(onClick = onClickAction)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                ImageVector.vectorResource(id = vectorResourceId),
                contentDescription = stringResource(id = R.string.post_action),
                tint = Color.Gray,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = text, fontWeight = FontWeight.Medium, color = Color.Gray, fontSize = 12.sp)
        }
    }
}

@Preview
@Composable
fun PostActionPreview() {
    PostAction(
        vectorResourceId = R.drawable.baseline_emoji_events_cyan_50_48dp,
        text = stringResource(R.string.award),
        onClickAction = {}
    )
}

@Preview
@Composable
fun HeaderPreview() {
    Column {
        HeaderView(DEFAULT_POST)
    }
}

