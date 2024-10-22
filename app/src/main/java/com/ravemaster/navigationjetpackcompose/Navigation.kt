package com.ravemaster.navigationjetpackcompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation

@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.MainScreen.route ){
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController)
        }
        composable (
            route = Screen.DetailScreen.route+"/{argument}",
            arguments = listOf(
                navArgument("argument"){
                    type = NavType.StringType
                    defaultValue = "Moses"
                    nullable = true
                }
            )
        ){ entry ->
            DetailScreen(argument = entry.arguments?.getString("argument") )
        }
        composable(route = Screen.BottomNavigationScreen.route){
            BottomNavigationScreen(navController = navController)
        }
    }
}

@Composable
fun MainScreen(navController: NavController, modifier: Modifier = Modifier) {

    var inputText by remember {
        mutableStateOf("")
    }
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ){
        OutlinedTextField(
            label = { Text(text = "Enter argument") },
            modifier = Modifier
                .padding(10.dp),
            value = inputText,
            onValueChange = {
            inputText = it
        })

        OutlinedButton(
            onClick = {
                navController.navigate(Screen.DetailScreen.withArgs(inputText))
            },
            ) {
            Text(text = "Navigate to details screen")
        }

        ElevatedButton(
            onClick = {
                navController.navigate(Screen.BottomNavigationScreen.route)
            },
        ) {
            Text(text = "Navigate to details screen")
        }

    }
}

@Composable
fun DetailScreen(argument: String?, modifier: Modifier = Modifier) {
    Box (
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)

    ){
        Text(text = argument ?: "No argument")
    }
}
@Composable
fun HomeScreenMain( modifier: Modifier = Modifier) {
    Box (
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)

    ){
        Text("No argument")
    }
}
@Composable
fun OffersScreenMain(modifier: Modifier = Modifier) {
    Box (
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green)

    ){
        Text("No argument")
    }
}
@Composable
fun SettingsScreenMain(modifier: Modifier = Modifier) {
    Box (
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow)

    ){
        Text("No argument")
    }
}


@Preview(showSystemUi = true)
@Composable
private fun MainScreenPreview() {
    DetailScreen(argument = null)
}