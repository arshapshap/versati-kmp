package core.navigation.state

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable

@Immutable
data class AppBarState(
    val currentRoute: String = "",
    val title: String = "",
    val showArrowBack: Boolean = false,
    val actions: (@Composable RowScope.() -> Unit)? = null,
    val showBottomBar: Boolean = true
)