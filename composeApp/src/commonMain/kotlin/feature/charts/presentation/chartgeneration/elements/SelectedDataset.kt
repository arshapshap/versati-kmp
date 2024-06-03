package feature.charts.presentation.chartgeneration.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import feature.charts.presentation.chartgeneration.contract.DatasetState
import org.jetbrains.compose.resources.stringResource
import versati.composeapp.generated.resources.Res
import versati.composeapp.generated.resources.dataset_with_number

@Composable
internal fun SelectedDataset(
    dataset: DatasetState,
    index: Int,
    isError: Boolean,
    showDeleteButton: Boolean,
    onDatasetDelete: (Int) -> Unit
) {
    val buttonColor =
        if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
    OutlinedButton(
        onClick = { },
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = buttonColor
        ),
        border = ButtonDefaults.outlinedButtonBorder.copy(
            brush = SolidColor(buttonColor)
        )
    ) {
        Text(text = dataset.label.ifEmpty {
            stringResource(Res.string.dataset_with_number, index + 1)
        })
        if (showDeleteButton) {
            Spacer(modifier = Modifier.padding(4.dp))
            Icon(
                imageVector = Icons.Default.Clear,
                contentDescription = null,
                tint = buttonColor,
                modifier = Modifier.clickable { onDatasetDelete(index) }
            )
        }
    }
}