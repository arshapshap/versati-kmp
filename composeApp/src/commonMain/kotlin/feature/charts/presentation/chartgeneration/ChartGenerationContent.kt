package feature.charts.presentation.chartgeneration

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import core.designsystem.elements.ButtonWithLoading
import core.designsystem.theme.ButtonHeight
import feature.charts.domain.model.ChartType
import feature.charts.presentation.chartgeneration.contract.ChartGenerationState
import feature.charts.presentation.chartgeneration.contract.DatasetState
import feature.charts.presentation.chartgeneration.elements.ChartTypeInput
import feature.charts.presentation.chartgeneration.elements.DatasetInput
import feature.charts.presentation.chartgeneration.elements.DatasetLabelInput
import feature.charts.presentation.chartgeneration.elements.LabelsInput
import feature.charts.presentation.chartgeneration.elements.SelectedDataset
import feature.charts.presentation.chartgeneration.elements.UnselectedDataset
import feature.charts.presentation.common.ui.ChartImage
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import versati.composeapp.generated.resources.Res
import versati.composeapp.generated.resources.add_dataset
import versati.composeapp.generated.resources.create_chart
import versati.composeapp.generated.resources.share_chart

@Composable
fun ChartGenerationContent(
    state: ChartGenerationState,
    onLabelsChange: (String) -> Unit = { },
    onChartTypeChange: (ChartType) -> Unit = { },
    onDatasetExpand: (Int) -> Unit = { },
    onDatasetDelete: (Int) -> Unit = { },
    onDatasetCreate: () -> Unit = { },
    onDatasetLabelChange: (Int, String) -> Unit = { _, _ -> },
    onDatasetChange: (Int, String) -> Unit = { _, _ -> },
    onCreateClick: () -> Unit = { },
    onShareClick: () -> Unit = { },
    onImageLoadingSuccess: () -> Unit = { },
    onImageLoadingError: () -> Unit = { },
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        ChartImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(vertical = 16.dp),
            imageUrl = state.chartImageUrl,
            showHint = state.loadingNumber == 1 && state.loading,
            onSuccess = onImageLoadingSuccess,
            onError = onImageLoadingError
        )
        LabelsInput(
            modifier = Modifier.padding(top = 8.dp),
            text = state.labels,
            onValueChange = onLabelsChange,
            isError = state.showLabelsInputError
        )
        ChartTypeInput(
            modifier = Modifier.padding(vertical = 8.dp),
            chartType = state.chartType,
            onValueChange = onChartTypeChange
        )
        Datasets(
            state = state,
            onDatasetExpand = onDatasetExpand,
            onDatasetDelete = onDatasetDelete,
            onDatasetCreate = onDatasetCreate,
            onDatasetLabelChange = onDatasetLabelChange,
            onDatasetChange = onDatasetChange,
        )
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .height(ButtonHeight)
        ) {
            ButtonWithLoading(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(4f),
                onClick = onCreateClick,
                text = stringResource(Res.string.create_chart),
                loading = state.loading,
                textStyle = MaterialTheme.typography.headlineSmall,
                textFontWeight = FontWeight.Bold
            )
            if (state.success && false) { // TODO: добавить шэринг ?
                Spacer(modifier = Modifier.padding(4.dp))
                Button(
                    onClick = onShareClick,
                    modifier = Modifier
                        .aspectRatio(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = stringResource(Res.string.share_chart)
                    )
                }
            }
        }
    }
}

@Composable
private fun Datasets(
    state: ChartGenerationState,
    onDatasetExpand: (Int) -> Unit,
    onDatasetDelete: (Int) -> Unit,
    onDatasetCreate: () -> Unit,
    onDatasetLabelChange: (Int, String) -> Unit,
    onDatasetChange: (Int, String) -> Unit,
) {
    Column {
        LazyRow {
            itemsIndexed(state.datasets) { i, it ->
                if (state.expandedDataset == i) {
                    SelectedDataset(
                        dataset = it,
                        index = i,
                        isError = it.showDataInputError || it.showLabelInputError,
                        showDeleteButton = state.datasets.size > 1,
                        onDatasetDelete = onDatasetDelete
                    )
                } else {
                    UnselectedDataset(
                        dataset = it,
                        index = i,
                        isError = it.showDataInputError || it.showLabelInputError,
                        onDatasetExpand = onDatasetExpand
                    )
                }
                Spacer(modifier = Modifier.padding(4.dp))
            }
            item {
                IconButton(
                    onClick = { onDatasetCreate() }
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(Res.string.add_dataset),
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
        DatasetOptions(
            state = state,
            onDatasetLabelChange = {
                onDatasetLabelChange(state.expandedDataset, it)
            },
            onDatasetChange = {
                onDatasetChange(state.expandedDataset, it)
            }
        )
    }
}

@Composable
private fun DatasetOptions(
    state: ChartGenerationState,
    onDatasetLabelChange: (String) -> Unit,
    onDatasetChange: (String) -> Unit,
) {
    Column {
        DatasetLabelInput(
            modifier = Modifier.padding(top = 8.dp),
            text = state.datasets[state.expandedDataset].label,
            onValueChange = onDatasetLabelChange,
            isError = state.datasets[state.expandedDataset].showLabelInputError
        )
        DatasetInput(
            modifier = Modifier.padding(vertical = 8.dp),
            text = state.datasets[state.expandedDataset].data,
            onValueChange = onDatasetChange,
            isError = state.datasets[state.expandedDataset].showDataInputError
        )
    }
}

@Preview
@Composable
private fun ChartGenerationContentPreview() {
    val state = ChartGenerationState(
        datasets = listOf(
            DatasetState(
                label = "Dataset 1"
            ),
            DatasetState(
                label = "lalala"
            ),
            DatasetState(
                label = ""
            )
        ),
        expandedDataset = 1
    )
    ChartGenerationContent(
        state = state
    )
}