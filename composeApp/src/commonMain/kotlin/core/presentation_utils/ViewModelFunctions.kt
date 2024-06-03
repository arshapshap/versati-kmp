package core.presentation_utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import org.koin.core.parameter.ParametersDefinition
import org.orbitmvi.orbit.ContainerHost

@Composable
expect inline fun <reified T : ViewModel> getViewModel(noinline parameters: ParametersDefinition? = null): T

@Composable
expect fun <STATE : Any, SIDE_EFFECT : Any> ContainerHost<STATE, SIDE_EFFECT>.collectSideEffect(
    sideEffect: (suspend (sideEffect: SIDE_EFFECT) -> Unit)
)

@Composable
expect fun <STATE : Any, SIDE_EFFECT : Any> ContainerHost<STATE, SIDE_EFFECT>.collectAsState(): State<STATE>