package feature.qrcodes.presentation.qrcodegeneration.elements

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import versati.composeapp.generated.resources.Res
import versati.composeapp.generated.resources.data
import versati.composeapp.generated.resources.data_hint

@Composable
internal fun DataInput(
    modifier: Modifier,
    text: String,
    onValueChange: (String) -> Unit,
    isError: Boolean
) {
    OutlinedTextField(
        value = text,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Info,
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
        modifier = modifier
            .fillMaxWidth(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Uri),
        label = { Text(text = stringResource(Res.string.data)) },
        placeholder = { Text(text = stringResource(Res.string.data_hint)) },
        onValueChange = onValueChange,
        isError = isError
    )
}

@Preview
@Composable
private fun DataInputPreview() {
    DataInput(
        modifier = Modifier,
        text = "https://google.com",
        isError = false,
        onValueChange = { }
    )
}