package com.arshapshap.versati.kmp.main.elements

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.arshapshap.versati.kmp.core.navigation.features.ChartsFeature
import com.arshapshap.versati.kmp.core.navigation.features.ImageParsingFeature
import com.arshapshap.versati.kmp.core.navigation.features.QRCodesFeature
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import versati.composeapp.generated.resources.Res
import versati.composeapp.generated.resources.charts
import versati.composeapp.generated.resources.ic_create_chart
import versati.composeapp.generated.resources.ic_create_qr_code
import versati.composeapp.generated.resources.ic_image_parsing
import versati.composeapp.generated.resources.image_parsing
import versati.composeapp.generated.resources.qr_codes

@Composable
internal fun BottomBar(
    navController: NavHostController
) {
    val screens = listOf(
        Screen.QRCodes,
        Screen.ImageParsing,
        Screen.Charts
    )
    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.secondaryContainer,
        modifier = Modifier
    ) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route
        screens.forEach { screen ->
            BottomNavigationItem(
                selected = currentRoute?.startsWith(screen.route) ?: false,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(screen.iconRes),
                        contentDescription = stringResource(screen.labelRes)
                    )
                },
                label = {
                    Text(
                        text = stringResource(screen.labelRes),
                        style = MaterialTheme.typography.labelLarge
                    )
                },
                alwaysShowLabel = false
            )
        }
    }
}

private sealed class Screen(
    val route: String,
    val labelRes: StringResource,
    val iconRes: DrawableResource
) {

    data object Charts : Screen(
        route = ChartsFeature.featureRoute,
        labelRes = Res.string.charts,
        iconRes = Res.drawable.ic_create_chart
    )

    data object ImageParsing : Screen(
        route = ImageParsingFeature.featureRoute,
        labelRes = Res.string.image_parsing,
        iconRes = Res.drawable.ic_image_parsing
    )

    data object QRCodes : Screen(
        route = QRCodesFeature.featureRoute,
        labelRes = Res.string.qr_codes,
        iconRes = Res.drawable.ic_create_qr_code
    )
}