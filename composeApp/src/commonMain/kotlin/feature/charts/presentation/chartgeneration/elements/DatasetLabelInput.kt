package feature.charts.presentation.chartgeneration.elements

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import versati.composeapp.generated.resources.Res
import versati.composeapp.generated.resources.dataset_label
import versati.composeapp.generated.resources.dataset_label_example
import versati.composeapp.generated.resources.ic_label

@Composable
internal fun DatasetLabelInput(
    modifier: Modifier,
    text: String,
    onValueChange: (String) -> Unit,
    isError: Boolean
) {
    OutlinedTextField(
        value = text,
        leadingIcon = {
            Icon(
                painter = painterResource(Res.drawable.ic_label),
                tint = MaterialTheme.colorScheme.outline,
                contentDescription = null
            )
        },
        trailingIcon = {
            if (isError) Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = null
            )
        },
        modifier = modifier.fillMaxWidth(),
        singleLine = true,
        label = { Text(text = stringResource(Res.string.dataset_label)) },
        placeholder = { Text(text = stringResource(Res.string.dataset_label_example)) },
        onValueChange = onValueChange,
        isError = isError
    )
}

@Preview
@Composable
private fun DatasetLabelInputPreview() {
    DatasetLabelInput(
        modifier = Modifier,
        text = "Users",
        isError = false,
        onValueChange = { }
    )
}