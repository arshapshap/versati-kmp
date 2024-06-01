package core.presentation_utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.flowWithLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import org.koin.core.parameter.ParametersDefinition
import org.orbitmvi.orbit.ContainerHost

@Composable
actual inline fun <reified T : ViewModel> getViewModel(noinline parameters: ParametersDefinition?): T {
    // TODO: сделать, чтобы при переходе между экранами вьюмодель не пересоздавалась
    return koinInject<T>(parameters = parameters)
}

@Composable
actual fun <STATE : Any, SIDE_EFFECT : Any> ContainerHost<STATE, SIDE_EFFECT>.collectSideEffect(
    sideEffect: suspend (sideEffect: SIDE_EFFECT) -> Unit
) {
    CoroutineScope(Dispatchers.Main).launch {
        container.sideEffectFlow.collect { sideEffect(it) }
    }
}

@Composable
actual fun <STATE : Any, SIDE_EFFECT : Any> ContainerHost<STATE, SIDE_EFFECT>.collectAsState(): State<STATE> {
    val stateFlow = container.stateFlow
    val lifecycleOwner = LocalLifecycleOwner.current

    val stateFlowLifecycleAware = remember(stateFlow, lifecycleOwner) {
        stateFlow.flowWithLifecycle(lifecycleOwner.lifecycle)
    }

    val initialValue = stateFlow.value
    return stateFlowLifecycleAware.collectAsState(initialValue)
}