package core.presentation_utils

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.ParametersDefinition
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.compose.collectAsState as collectOrbitVMAsState
import org.orbitmvi.orbit.compose.collectSideEffect as collectOrbitSideEffect

@Composable
actual inline fun <reified T : ViewModel> getViewModel(noinline parameters: ParametersDefinition?): T {
    return koinViewModel<T>(parameters = parameters)
}

@SuppressLint("ComposableNaming")
@Composable
actual fun <STATE : Any, SIDE_EFFECT : Any> ContainerHost<STATE, SIDE_EFFECT>.collectSideEffect(
    sideEffect: suspend (sideEffect: SIDE_EFFECT) -> Unit
) {
    collectOrbitSideEffect(sideEffect = sideEffect)
}

@Composable
actual fun <STATE : Any, SIDE_EFFECT : Any> ContainerHost<STATE, SIDE_EFFECT>.collectAsState(): State<STATE> {
    return collectOrbitVMAsState()
}