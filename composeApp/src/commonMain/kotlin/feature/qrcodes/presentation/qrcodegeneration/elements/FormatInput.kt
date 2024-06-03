package feature.qrcodes.presentation.qrcodegeneration.elements

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import core.designsystem.elements.DropdownInput
import feature.qrcodes.domain.model.ImageFormat
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import versati.composeapp.generated.resources.Res
import versati.composeapp.generated.resources.ic_file
import versati.composeapp.generated.resources.image_format

@Composable
internal fun FormatInput(
    modifier: Modifier,
    format: ImageFormat,
    onValueChange: (ImageFormat) -> Unit
) {
    DropdownInput(
        modifier = modifier,
        valueString = format.name,
        onSelect = {
            onValueChange(ImageFormat.valueOf(it))
        },
        entries = ImageFormat.entries.map { it.name },
        leadingIcon = {

            Icon(
                painter = painterResource(Res.drawable.ic_file),
                tint = MaterialTheme.colorScheme.outline,
                contentDescription = null
            )
        },
        label = stringResource(Res.string.image_format)
    )
}

@Preview
@Composable
private fun FormatInputPreview() {
    FormatInput(
        modifier = Modifier,
        format = ImageFormat.PNG,
        onValueChange = { }
    )
}