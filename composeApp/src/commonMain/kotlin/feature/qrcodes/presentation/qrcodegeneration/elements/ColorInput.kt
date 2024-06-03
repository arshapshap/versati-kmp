package feature.qrcodes.presentation.qrcodegeneration.elements

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import versati.composeapp.generated.resources.Res
import versati.composeapp.generated.resources.ic_qr_code
import versati.composeapp.generated.resources.qr_code_color

@Composable
internal fun ColorInput(
    modifier: Modifier,
    iconRes: DrawableResource,
    titleRes: StringResource,
    colorString: String,
    color: Int?,
    isError: Boolean,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = colorString,
        leadingIcon = {
            Icon(
                painter = painterResource(iconRes),
                tint = color?.let { Color(it.withAlpha()) }
                    ?: MaterialTheme.colorScheme.outline,
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
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        label = { Text(text = stringResource(titleRes)) },
        isError = isError,
        onValueChange = onValueChange
    )
}

private fun Int.withAlpha() = this + 0xFF000000

@Preview
@Composable
private fun ColorInputPreview() {
    ColorInput(
        modifier = Modifier,
        iconRes = Res.drawable.ic_qr_code,
        titleRes = Res.string.qr_code_color,
        colorString = "ff0000",
        color = 0xff0000,
        isError = false,
        onValueChange = { }
    )
}