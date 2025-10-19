package dev.tiagoaguiar.kingburguer.compose.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.tiagoaguiar.kingburguer.compose.signup.SignUpScreen
import dev.tiagoaguiar.kingburguer.ui.theme.KingBurguerTheme
import dev.tiagoaguiar.kingburguer.viewmodels.SignUpViewModel


@Composable
fun HomeScreen(
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            topBar = {

            },
        ) { contentPadding ->
            HomeContentScreen(modifier = Modifier.padding(top = contentPadding.calculateTopPadding()))
        }
    }
}

@Composable
private fun HomeContentScreen(
    modifier: Modifier,
) {
    Surface(
        modifier = modifier.fillMaxSize()
    ) {
        Text("Home Screen")
    }
}

@Composable
@Preview(showBackground = true)
fun LightHomeScreenPreview() {
    KingBurguerTheme(dynamicColor = false, darkTheme = false) {
        HomeScreen()
    }
}

@Composable
@Preview(showBackground = true)
fun DarkHomeScreenPreview() {
    KingBurguerTheme(dynamicColor = false, darkTheme = true) {
        HomeScreen()
    }
}