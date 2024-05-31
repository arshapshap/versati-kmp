package feature.qrcodes.presentation.qrcodegeneration.elements

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import versati.composeapp.generated.resources.Res
import versati.composeapp.generated.resources.ic_width
import versati.composeapp.generated.resources.width

@Composable
internal fun SizeInput(
    modifier: Modifier,
    size: String,
    titleRes: StringResource,
    iconRes: DrawableResource,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = size,
        leadingIcon = {
            Icon(
                painter = painterResource(iconRes),
                tint = MaterialTheme.colorScheme.outline,
                contentDescription = null
            )
        },
        modifier = modifier,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        label = { Text(text = stringResource(titleRes)) },
        onValueChange = onValueChange
    )
}

@Preview
@Composable
private fun SizeInputPreview() {
    SizeInput(
        modifier = Modifier,
        size = "200",
        titleRes = Res.string.width,
        iconRes = Res.drawable.ic_width,
        onValueChange = { }
    )
}