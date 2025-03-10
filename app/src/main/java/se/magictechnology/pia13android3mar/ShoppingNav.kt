package se.magictechnology.pia13android3mar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun ShoppingNav(shopviewmodel : ShopViewModel) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "shopping") {
        composable("shopping") {
            ShoppingScreen(shopviewmodel, goInfo = {
                navController.navigate("info")
            })
        }
        composable("info") {
            ShopInfo(shopviewmodel)
        }

    }
}