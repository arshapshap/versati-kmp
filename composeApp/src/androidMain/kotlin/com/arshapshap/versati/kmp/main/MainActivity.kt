package com.arshapshap.versati.kmp.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.arshapshap.versati.kmp.core.designsystem.theme.VersatiTheme
import com.arshapshap.versati.kmp.core.navigation.features.AuthFeature
import com.arshapshap.versati.kmp.core.navigation.features.SettingsFeature
import com.arshapshap.versati.kmp.core.navigation.state.AppBarState
import com.arshapshap.versati.kmp.main.elements.BottomBar
import com.arshapshap.versati.kmp.main.elements.TopBar
import com.arshapshap.versati.kmp.main.navigation.MainNavHost

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val startDestination = AuthFeature.featureRoute
        setContent {
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
                                startDestination = startDestination
                            ) { state ->
                                if (navController.currentDestination?.route == state.currentRoute)
                                    appBarState = state
                            }
                        },
                        bottomBar = {
                            if (appBarState.showBottomBar)
                                BottomBar(
                                    navController = navController
                                )
                        }
                    )
                }
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
}