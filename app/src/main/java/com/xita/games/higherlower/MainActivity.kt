package com.xita.games.higherlower

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.xita.games.higherlower.pages.HomePage
import com.xita.games.higherlower.pages.PlayerNamesPage
import com.xita.games.higherlower.pages.SplashScreen
import com.xita.games.higherlower.pages.UpdatePage
import com.xita.games.higherlower.ui.theme.XitaGamesHigherLowerTheme


//
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            XitaGamesHigherLowerTheme(darkTheme = false) {
                NavigationGraph(rememberNavController(),this)
            }
        }
    }
}

@Composable
fun NavigationGraph(
    navController: NavHostController,
    activity: Activity
) {

//    composable("splash") { SplashScreen(navController) }
    NavHost(
        navController = navController,
        startDestination =  "splash"
//        startDestination =  "splash"
    ) {
        composable("home/{playerOneName}/{playerTwoName}/{playerOneGender}/{playerTwoGender}") {
            HomePage(navController)
        }
        composable("splash") { SplashScreen(navController,activity) }

        composable("playerNames") { PlayerNamesPage(navController) }
//        composable("onboardingScreen") {
//            OnboardingScreen(navController,
//                { navController.navigate("login") })
//        }
//        composable("login") {
//            LoginPage(
//                navController,
//                chadventDatabaseViewModel = chadventDatabaseViewModel
//            )
//        }
//        composable(route = "mainScreen") {
//            MainScreen(
//                navController,
//                chadventDatabaseViewModel = chadventDatabaseViewModel
//            )
//        }
//
        composable(route = "updatePage") {
            UpdatePage(

            )
        }
    }
}
//



