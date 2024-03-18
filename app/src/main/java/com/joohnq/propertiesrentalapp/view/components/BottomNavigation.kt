package com.joohnq.propertiesrentalapp.view.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.joohnq.propertiesrentalapp.R
import com.joohnq.propertiesrentalapp.view.Screen
import com.joohnq.propertiesrentalapp.view.theme.GradientGray7D7F88
import com.joohnq.propertiesrentalapp.view.theme.GradientPurpleToPurple
import com.joohnq.propertiesrentalapp.view.theme.White

data class BottomNavItem(
    val title: String,
    val route: String,
    val selectedIcon: Int,
    val unSelectedIcon: Int,
    val hasNews: Boolean,
    val badges: Int,
)

@Composable
fun MainBottomNavigation(
    navController: NavController,
    selectedItem: Int,
    onChangeBottomBar: (Int) -> Unit
) {
    val items = listOf(
        BottomNavItem(
            title = stringResource(id = R.string.home),
            route = Screen.HomeScreen.rout,
            selectedIcon = R.drawable.ic_home_filled,
            unSelectedIcon = R.drawable.ic_home_outline,
            hasNews = false,
            badges = 0
        ),
        BottomNavItem(
            title = stringResource(id = R.string.explore),
            route = Screen.ExploreScreen.rout,
            selectedIcon = R.drawable.ic_explore_filled,
            unSelectedIcon = R.drawable.ic_explore_outline,
            hasNews = false,
            badges = 0
        ),
        BottomNavItem(
            title = stringResource(id = R.string.chat),
            route = Screen.ChatScreen.rout,
            selectedIcon = R.drawable.ic_chat_filled,
            unSelectedIcon = R.drawable.ic_chat_outline,
            hasNews = false,
            badges = 0
        ),
        BottomNavItem(
            title = stringResource(id = R.string.saved),
            route = Screen.SavedScreen.rout,
            selectedIcon = R.drawable.ic_heart_filled,
            unSelectedIcon = R.drawable.ic_heart_outline,
            hasNews = false,
            badges = 0
        ),
        BottomNavItem(
            title = stringResource(id = R.string.profile),
            route = Screen.ProfileScreen.rout,
            selectedIcon = R.drawable.ic_profile_filled,
            unSelectedIcon = R.drawable.ic_profile_outline,
            hasNews = false,
            badges = 0
        )
    )
    NavigationBar(containerColor = White) {
        items.forEachIndexed { index, item ->
            val isSelected = selectedItem == index
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent,
                ),
                label = {
                    Text(
                        text = item.title,
                        style = p_12_medium_inter.copy(brush = if (isSelected) GradientPurpleToPurple else GradientGray7D7F88)
                    )
                },
                icon = {
                    Icon(
                        modifier = Modifier
                            .graphicsLayer(alpha = 0.99f)
                            .drawWithCache {
                                onDrawWithContent {
                                    drawContent()
                                    drawRect(
                                        if (isSelected) GradientPurpleToPurple else GradientGray7D7F88,
                                        blendMode = BlendMode.SrcAtop
                                    )
                                }
                            },
                        painter = painterResource(id = if (isSelected) item.selectedIcon else item.unSelectedIcon),
                        contentDescription = null,
                    )
                },
                selected = isSelected,
                onClick = {
                    onChangeBottomBar(index)
                    navController.navigate(item.route)
                },
            )
        }
    }
}
