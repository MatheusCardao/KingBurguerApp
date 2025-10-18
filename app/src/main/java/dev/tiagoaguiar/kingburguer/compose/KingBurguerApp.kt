package dev.tiagoaguiar.kingburguer.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.tiagoaguiar.kingburguer.compose.login.LoginScreen
import dev.tiagoaguiar.kingburguer.compose.signup.SignUpScreen
import dev.tiagoaguiar.kingburguer.ui.theme.KingBurguerTheme

@Composable
fun KingBurguerApp() {
    val navController = rememberNavController()
    KingBurguerNavHost(navController)
}

@Composable
fun KingBurguerNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.LOGIN.route
    ) {
        composable(Screen.LOGIN.route) {
            LoginScreen(onSingUpClick = {
                navController.navigate(Screen.SIGNUP.route)
            })
        }
        composable(Screen.SIGNUP.route) {
            SignUpScreen(onNavigationClick = {
                navController.navigateUp()
            })
        }
    }
}

@Composable
@Preview(showBackground = true)
fun KingBurguerPreview() {
    KingBurguerTheme {
        KingBurguerApp()
    }
}