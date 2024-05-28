package com.arshapshap.versati.kmp.main.elements

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.arshapshap.versati.kmp.core.navigation.state.AppBarState
import org.jetbrains.compose.resources.stringResource
import versati.composeapp.generated.resources.Res
import versati.composeapp.generated.resources.back
import versati.composeapp.generated.resources.settings

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TopBar(
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
    state: AppBarState = AppBarState(),
    onSettingsClick: () -> Unit = { }
) {
    TopAppBar(
        title = {
            Text(
                text = state.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            if (state.showArrowBack) {
                val backPressDispatcher = LocalOnBackPressedDispatcherOwner.current
                IconButton(
                    onClick = {
                        backPressDispatcher?.onBackPressedDispatcher?.onBackPressed()
                    },
                    content = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(Res.string.back)
                        )
                    }
                )
            } else {
                IconButton(
                    onClick = onSettingsClick,
                    content = {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = stringResource(Res.string.settings)
                        )
                    }
                )
            }
        },
        actions = {
            state.actions?.invoke(this)
        },
        scrollBehavior = scrollBehavior
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun Preview() {
    val state = AppBarState(
        title = "Main"
    )
    TopBar(
        state = state
    )
}