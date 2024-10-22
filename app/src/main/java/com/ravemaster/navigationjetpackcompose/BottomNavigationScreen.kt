package com.ravemaster.navigationjetpackcompose


import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

val bottomItems = listOf(
    NavigationItems(
        title = "Home",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        hasNews = false,
        badgeCount = null,
        route = Screen.HomeScreen.route
    ),
    NavigationItems(
        title = "Cart",
        selectedIcon = Icons.Filled.ShoppingCart,
        unselectedIcon = Icons.Outlined.ShoppingCart,
        hasNews = false,
        badgeCount = 7,
        route = Screen.OffersScreen.route
    ),
    NavigationItems(
        title = "Settings",
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings,
        hasNews = true,
        badgeCount = null,
        route = Screen.SettingsScreen.route
    ),
)

@Composable
fun Navigation2(controller: NavController) {

}



@Composable
fun BottomNavigationScreen(navController: NavController, modifier: Modifier = Modifier) {

    val navController2 = rememberNavController()

    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }

    BackHandler {
        navController.popBackStack()
    }

    Scaffold (
        bottomBar = {
            NavigationBar {
                bottomItems.forEachIndexed { index, navigationItems ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = {
                            selectedItemIndex = index
                            navController2.navigate(navigationItems.route)
                        },
                        icon = {
                            BadgedBox(badge = {
                                if (navigationItems.badgeCount != null){
                                    Badge {
                                        Text(text = navigationItems.badgeCount.toString())
                                    }
                                } else if (navigationItems.hasNews) {
                                    Badge()
                                }
                            }) {
                                Icon(
                                    imageVector =
                                    if (index == selectedItemIndex) navigationItems.selectedIcon
                                    else navigationItems.unselectedIcon,
                                    contentDescription = navigationItems.title)
                            }
                        },
                        label = {
                            Text(text = navigationItems.title)
                        },
                        alwaysShowLabel = true)
                }
            }
        }
    ){ innerPadding ->

        NavHost(navController = navController2, startDestination = Screen.HomeScreen.route,modifier = Modifier.padding(innerPadding)) {
            composable(route = Screen.HomeScreen.route) {
                HomeScreenMain()
            }
            composable(route = Screen.OffersScreen.route) {
                OffersScreenMain()
            }
            composable(route = Screen.SettingsScreen.route) {
                SettingsScreenMain()
            }
        }
    }
}