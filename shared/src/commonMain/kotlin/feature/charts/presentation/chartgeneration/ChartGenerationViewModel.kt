package feature.charts.presentation.chartgeneration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.charts.domain.model.ChartType
import feature.charts.domain.usecase.CreateChartUseCase
import feature.charts.domain.usecase.GetChartInfoByIdUseCase
import feature.charts.presentation.chartgeneration.contract.ChartGenerationSideEffect
import feature.charts.presentation.chartgeneration.contract.ChartGenerationState
import feature.charts.presentation.chartgeneration.contract.DatasetState
import feature.charts.presentation.chartgeneration.mapper.ChartGenerationStateMapper
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.container
import org.orbitmvi.orbit.syntax.simple.SimpleSyntax
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import kotlin.math.max

private typealias IntentSyntax = SimpleSyntax<ChartGenerationState, ChartGenerationSideEffect>

class ChartGenerationViewModel(
    chartInfoId: Long,
    private val createChartUseCase: CreateChartUseCase,
    private val getChartInfoByIdUseCase: GetChartInfoByIdUseCase,
    private val mapper: ChartGenerationStateMapper
) : ContainerHost<ChartGenerationState, ChartGenerationSideEffect>, ViewModel() {

    override val container =
        viewModelScope
            .container<ChartGenerationState, ChartGenerationSideEffect>(ChartGenerationState())

    init {
        if (chartInfoId != 0L)
            loadChartInfo(chartInfoId)
    }

    fun createChart() = intent {
        if (state.success && !state.optionsChanged) return@intent
        if (!checkIfOptionsValid()) return@intent

        if (state.chartImageUrl.isNotEmpty() && !state.success)
            retryLoading()
        else
            loadChart()
    }

    fun shareChart() = intent {
        postSideEffect(ChartGenerationSideEffect.ShareChart)
    }

    fun navigateToChartsHistory() = intent {
        postSideEffect(ChartGenerationSideEffect.NavigateToChartsHistory)
    }

    fun onImageLoadingSuccess() = intent {
        reduce { state.copy(success = true, loading = false) }
    }

    fun onImageLoadingError() = intent {
        if (state.loading)
            postSideEffect(ChartGenerationSideEffect.TimeoutError)
        reduce { state.copy(success = false, loading = false) }
    }

    @OptIn(OrbitExperimental::class)
    fun updateLabels(value: String) = blockingIntent {
        reduce { state.copy(labels = value, showLabelsInputError = false, optionsChanged = true) }
    }

    fun expandDataset(index: Int) = intent {
        reduce { state.copy(expandedDataset = index) }
    }

    fun deleteDataset(index: Int) = intent {
        reduce {
            state.copy(
                datasets = state.datasets.minus(state.datasets[index]),
                expandedDataset = max(index - 1, 0),
                optionsChanged = true
            )
        }
    }

    fun createDataset() = intent {
        reduce {
            state.copy(
                datasets = state.datasets.plus(DatasetState()),
                expandedDataset = state.datasets.size,
                optionsChanged = true
            )
        }
    }

    @OptIn(OrbitExperimental::class)
    fun updateDatasetLabel(datasetIndex: Int, label: String) = blockingIntent {
        reduce {
            state.copy(
                datasets = state.datasets.replace(
                    index = datasetIndex,
                    element = state.datasets[datasetIndex].copy(
                        label = label,
                        showLabelInputError = false
                    )
                ),
                optionsChanged = true
            )
        }
    }

    @OptIn(OrbitExperimental::class)
    fun updateDataset(datasetIndex: Int, data: String) = blockingIntent {
        reduce {
            state.copy(
                datasets = state.datasets.replace(
                    index = datasetIndex,
                    element = state.datasets[datasetIndex].copy(
                        data = data,
                        showDataInputError = false
                    )
                ),
                optionsChanged = true
            )
        }
    }

    @OptIn(OrbitExperimental::class)
    fun updateChartType(chartType: ChartType) = blockingIntent {
        reduce { state.copy(chartType = chartType, optionsChanged = true) }
    }

    private suspend fun IntentSyntax.retryLoading() {
        val imageUrl = state.chartImageUrl
        reduce { state.copy(chartImageUrl = "") }
        // Thread.sleep(1000) TODO: тут нужно было подождать какое-то время между изменениями стейта
        reduce {
            state.copy(
                chartImageUrl = imageUrl,
                loading = true,
                optionsChanged = false,
            )
        }
    }

    private suspend fun IntentSyntax.loadChart() {
        reduce { state.copy(chartImageUrl = "", success = false) }
        val result = createChartUseCase(mapper.fromState(state))
        reduce {
            state.copy(
                chartImageUrl = result,
                loading = true,
                optionsChanged = false,
                loadingNumber = state.loadingNumber + 1
            )
        }
    }

    private fun loadChartInfo(id: Long) = intent {
        val chartInfo = getChartInfoByIdUseCase(id) ?: return@intent
        reduce {
            mapper.toState(chartInfo)
                .copy(optionsChanged = false, loading = true, loadingNumber = 1)
        }
    }

    private suspend fun IntentSyntax.checkIfOptionsValid(): Boolean {
        var error = state.labels.isBlank()
        reduce { state.copy(showLabelsInputError = state.labels.isBlank()) }

        state.datasets.forEachIndexed { index, dataset ->
            error = error || dataset.label.isBlank() || dataset.data.isBlank()
            reduce {
                state.copy(
                    datasets = state.datasets.replace(
                        index = index,
                        element = dataset.copy(
                            showLabelInputError = dataset.label.isBlank(),
                            showDataInputError = dataset.data.isBlank()
                        )
                    )
                )
            }
        }

        return !error
    }

    private fun <T> List<T>.replace(index: Int, element: T): List<T> = this
        .toMutableList()
        .apply {
            this[index] = element
        }
}