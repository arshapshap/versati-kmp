@file:OptIn(ExperimentalMaterial3Api::class)

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.compose.rememberNavController
import core.designsystem.theme.VersatiTheme
import core.navigation.features.ChartsFeature
import core.navigation.features.SettingsFeature
import core.navigation.state.AppBarState
import main.elements.BottomBar
import main.elements.TopBar
import main.navigation.MainNavHost
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun App() {
    val navController = rememberNavController()
    VersatiTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
            var appBarState by remember { mutableStateOf(AppBarState()) }
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    TopBar(
                        scrollBehavior = scrollBehavior,
                        state = appBarState,
                        onBackPressed = {
                            navController.popBackStack()
                        },
                        onSettingsClick = {
                            navController.navigate(SettingsFeature.Settings.destination()) {
                                launchSingleTop = true
                            }
                        }
                    )
                },
                content = {
                    MainNavHost(
                        modifier = Modifier.padding(it),
                        navController = navController,
                        startDestination = ChartsFeature.featureRoute
                    ) { state ->
                        if (navController.currentDestination?.route == state.currentRoute)
                            appBarState = state
                    }
                },
                bottomBar = {
                    if (appBarState.showBottomBar) {
                        BottomBar(
                            navController = navController
                        )
                    }
                }
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        topBar = { TopBar(scrollBehavior) },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {

            }
        },
        bottomBar = {
            //BottomBar()
        }
    )
}