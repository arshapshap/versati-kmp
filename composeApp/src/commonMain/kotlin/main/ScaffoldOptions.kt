package main

import androidx.compose.material3.SnackbarHostState
import core.navigation.state.AppBarState

data class ScaffoldOptions(
    val snackbarHostState: SnackbarHostState,
    val appBarConfigure: (AppBarState) -> Unit
)