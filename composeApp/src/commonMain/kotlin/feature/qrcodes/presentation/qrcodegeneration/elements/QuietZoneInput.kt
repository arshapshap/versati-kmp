package feature.qrcodes.presentation.qrcodegeneration.elements

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import versati.composeapp.generated.resources.Res
import versati.composeapp.generated.resources.ic_square
import versati.composeapp.generated.resources.quiet_zone

@Composable
internal fun QuietZoneInput(
    modifier: Modifier,
    quietZone: Int?,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = quietZone?.toString() ?: "",
        leadingIcon = {
            Icon(
                painter = painterResource(Res.drawable.ic_square),
                tint = MaterialTheme.colorScheme.outline,
                contentDescription = null
            )
        },
        modifier = modifier
            .fillMaxWidth(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        label = { Text(text = stringResource(Res.string.quiet_zone)) },
        onValueChange = onValueChange,
    )
}

@Preview
@Composable
private fun QuietZoneInputPreview() {
    QuietZoneInput(
        modifier = Modifier,
        quietZone = 3,
        onValueChange = { }
    )
}