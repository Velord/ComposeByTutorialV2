package com.example.composebytutorialv2.ui.section1.chapter4

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composebytutorialv2.R
import com.example.composebytutorialv2.ui.router.BackButtonHandler
import com.example.composebytutorialv2.ui.router.JetFundamentalsRouter
import com.example.composebytutorialv2.ui.router.Screen

data class BookCategory(
    val id: Int,
    @StringRes val title: Int,
    val books: List<Int>
)

val booksCategories = listOf(
    BookCategory(
        1, R.string.android,
        listOf(
            R.drawable.round_accessibility_red_400_48dp,
            R.drawable.round_accessible_deep_purple_a200_48dp,
            R.drawable.baseline_accessibility_new_black_48dp
        )
    ),
    BookCategory(
        2, R.string.kotlin,
        listOf(
            R.drawable.baseline_arrow_circle_down_red_100_24dp,
            R.drawable.baseline_arrow_circle_up_purple_50_48dp,
            R.drawable.baseline_arrow_right_alt_deep_purple_800_48dp
        )
    ),
    BookCategory(
        3, R.string.swift,
        listOf(
            R.drawable.baseline_account_balance_red_100_48dp,
            R.drawable.baseline_account_balance_wallet_pink_a200_48dp,
            R.drawable.baseline_account_box_blue_a200_48dp
        )
    )
)

@Preview
@Composable
fun BookListScreen() {
    MyBookList()
    
    BackButtonHandler {
        JetFundamentalsRouter.navigateTo(Screen.Navigation)
    }
}

@Composable
fun MyBookList() {
    LazyColumn {
        items(booksCategories) { ListItem(it) }
    }
}

@Composable
fun ListItem(bookCategory: BookCategory, modifier: Modifier = Modifier) {
    Column(Modifier.padding(vertical = 16.dp, horizontal = 8.dp)) {
        Text(
            modifier = modifier.padding(start = 16.dp),
            text = stringResource(id = bookCategory.title),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.teal_700)
        )
        Spacer(modifier.height(8.dp))
        
        LazyRow {
            items(bookCategory.books) {
                BookImage(it)
            }
        }
    }
}

@Composable
fun BookImage(@DrawableRes imageRes: Int) {
    Image(
        modifier = Modifier.size(170.dp, 200.dp),
        painter = painterResource(id = imageRes),
        contentScale = ContentScale.Fit,
        contentDescription = stringResource(id = R.string.book_image)
    )
}
