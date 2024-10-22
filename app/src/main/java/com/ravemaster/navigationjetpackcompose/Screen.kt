package com.ravemaster.navigationjetpackcompose

sealed class Screen( val route:String) {
    object MainScreen : Screen("main_screen")
    object DetailScreen: Screen("detail_screen")
    object BottomNavigationScreen: Screen("bottom_navigation_screen")
    object HomeScreen: Screen("home_screen")
    object OffersScreen: Screen("offers_screen")
    object SettingsScreen: Screen("settings_screen")

    fun withArgs(vararg arg: String ): String {
        return buildString {
            append(route)
            arg.forEach { arg ->
                append("/$arg")
            }
        }
    }
}