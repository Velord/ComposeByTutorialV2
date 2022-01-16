package com.example.composebytutorialv2.ui.screen.section3

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.composebytutorialv2.R
import com.example.composebytutorialv2.navigation.section3.RedditRouter
import com.example.composebytutorialv2.navigation.section3.RedditScreen
import com.example.composebytutorialv2.ui.component.section3.chapter9.appDrawer.RedditAppDrawerView
import com.example.composebytutorialv2.ui.screen.section3.chapter10.HomeScreen
import com.example.composebytutorialv2.ui.screen.section3.chapter10.SubredditCollectionScreen
import com.example.composebytutorialv2.ui.screen.section3.chapter11.ChooseCommunityScreen
import com.example.composebytutorialv2.ui.theme.section3.RedditTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun RedditApp(viewModel: RedditViewModel) {
    RedditTheme {
        AppContent(viewModel)
    }
}

@Composable
private fun AppContent(viewModel: RedditViewModel) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    Crossfade(targetState = RedditRouter.currentScreen) { screenState: MutableState<RedditScreen> ->
        Scaffold(
            topBar = getTopBar(screenState.value, scaffoldState, coroutineScope),
            drawerContent = {
                RedditAppDrawerView(
                    closeDrawerAction = { coroutineScope.launch { scaffoldState.drawerState.close() } }
                )
            },
            scaffoldState = scaffoldState,
            bottomBar = {
                BottomNavigationComponent(screenState = screenState)
            },
            content = {
                MainScreenContainer(
                    modifier = Modifier.padding(bottom = 56.dp),
                    screenState = screenState,
                    viewModel = viewModel
                )
            }
        )
    }
}

fun getTopBar(
    screenState: RedditScreen,
    scaffoldState: ScaffoldState,
    coroutineScope: CoroutineScope
): @Composable (() -> Unit) =
    if (screenState == RedditScreen.MyProfile) { {} }
    else { { TopAppBar(scaffoldState = scaffoldState, coroutineScope = coroutineScope) } }

@Composable
fun TopAppBar(scaffoldState: ScaffoldState, coroutineScope: CoroutineScope) {
    val colors = MaterialTheme.colors

    TopAppBar(
        title = {
            Text(
                text = stringResource(RedditRouter.currentScreen.value.titleResId),
                color = colors.primaryVariant
            )
        },
        backgroundColor = colors.surface,
        navigationIcon = {
            IconButton(onClick = {
                coroutineScope.launch { scaffoldState.drawerState.open() }
            }) {
                Icon(
                    Icons.Filled.AccountCircle,
                    tint = Color.LightGray,
                    contentDescription = stringResource(id = R.string.account)
                )
            }
        }
    )
}

@Composable
private fun MainScreenContainer(
    modifier: Modifier = Modifier,
    screenState: MutableState<RedditScreen>,
    viewModel: RedditViewModel
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colors.background
    ) {
        when (screenState.value) {
            RedditScreen.Home -> HomeScreen(viewModel)
            RedditScreen.SubscriptionList -> SubredditCollectionScreen()
            RedditScreen.ChooseCommunity -> ChooseCommunityScreen(viewModel)
            RedditScreen.NewPost -> ChooseCommunityScreen(viewModel)
//            RedditScreen.MyProfile -> Profile()
        }
    }
}

@Composable
private fun BottomNavigationComponent(
    modifier: Modifier = Modifier,
    screenState: MutableState<RedditScreen>
) {
    var selectedItem by remember { mutableStateOf(0) }

    val items = listOf(
        NavigationItem(0, R.drawable.sharp_home_blue_600_48dp, R.string.home_icon, RedditScreen.Home),
        NavigationItem(
            1,
            R.drawable.round_list_green_700_48dp,
            R.string.subscription_icon,
            RedditScreen.SubscriptionList
        ),
        NavigationItem(2, R.drawable.twotone_addchart_red_400_48dp, R.string.post_icon, RedditScreen.NewPost),
    )
    BottomNavigation(modifier = modifier) {
        items.forEach {
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = it.vectorResId),
                        contentDescription = stringResource(id = it.contDesResId)
                    )
                },
                selected = selectedItem == it.index,
                onClick = {
                    selectedItem = it.index
                    screenState.value = it.screen
                }
            )
        }
    }
}

private data class NavigationItem(
    val index: Int,
    val vectorResId: Int,
    val contDesResId: Int,
    val screen: RedditScreen
)