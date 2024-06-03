package feature.qrcodes.presentation.qrcodegeneration

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import core.designsystem.elements.ButtonWithLoading
import core.designsystem.theme.ButtonHeight
import feature.qrcodes.domain.model.ImageFormat
import feature.qrcodes.presentation.common.ui.QRCodeImage
import feature.qrcodes.presentation.qrcodegeneration.contract.QRCodeGenerationState
import feature.qrcodes.presentation.qrcodegeneration.elements.ColorInput
import feature.qrcodes.presentation.qrcodegeneration.elements.DataInput
import feature.qrcodes.presentation.qrcodegeneration.elements.FormatInput
import feature.qrcodes.presentation.qrcodegeneration.elements.QuietZoneInput
import feature.qrcodes.presentation.qrcodegeneration.elements.SizeInput
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import versati.composeapp.generated.resources.Res
import versati.composeapp.generated.resources.advanced_options
import versati.composeapp.generated.resources.background_color
import versati.composeapp.generated.resources.create_qr_code
import versati.composeapp.generated.resources.height
import versati.composeapp.generated.resources.ic_background_grid
import versati.composeapp.generated.resources.ic_height
import versati.composeapp.generated.resources.ic_qr_code
import versati.composeapp.generated.resources.ic_width
import versati.composeapp.generated.resources.qr_code_color
import versati.composeapp.generated.resources.show_advanced_options
import versati.composeapp.generated.resources.width

@Composable
fun QRCodeGenerationContent(
    state: QRCodeGenerationState,
    onAdvancedOptionsExpand: () -> Unit = { },
    onDataChange: (String) -> Unit = { },
    onSizeChange: (String) -> Unit = { },
    onQRCodeColorChange: (String) -> Unit = { },
    onBackgroundColorChange: (String) -> Unit = { },
    onQuietZoneChange: (String) -> Unit = { },
    onFormatChange: (ImageFormat) -> Unit = { },
    onCreateClick: () -> Unit = { },
    onShareClick: () -> Unit = { },
    onImageLoadingSuccess: () -> Unit = { },
    onImageLoadingError: () -> Unit = { }
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        QRCodeImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(vertical = 16.dp),
            imageUrl = state.qrCodeImageUrl,
            onSuccess = onImageLoadingSuccess,
            onError = onImageLoadingError,
        )
        DataInput(
            modifier = Modifier
                .padding(vertical = 8.dp),
            text = state.data,
            onValueChange = onDataChange,
            isError = state.showDataFieldError,
        )
        Button(
            onClick = { onAdvancedOptionsExpand() },
        ) {
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = stringResource(Res.string.show_advanced_options),
                modifier = Modifier.rotate(if (state.advancedOptionsExpanded) 180f else 0f)
            )
            Text(text = stringResource(Res.string.advanced_options))
        }
        if (state.advancedOptionsExpanded) {
            AdvancedOptions(
                state = state,
                onSizeChange = onSizeChange,
                onQRCodeColorChange = onQRCodeColorChange,
                onBackgroundColorChange = onBackgroundColorChange,
                onQuietZoneChange = onQuietZoneChange,
                onFormatChange = onFormatChange
            )
        }
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp)
        ) {
            ButtonWithLoading(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(4f),
                onClick = onCreateClick,
                text = stringResource(Res.string.create_qr_code),
                loading = state.loading,
                textStyle = MaterialTheme.typography.headlineSmall,
                textFontWeight = FontWeight.Bold
            )
            if (state.success && false) { // TODO: добавить шэринг ?
                Spacer(modifier = Modifier.padding(4.dp))
                Button(
                    onClick = onShareClick,
                    modifier = Modifier
                        .height(ButtonHeight)
                        .aspectRatio(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Share QR-code"
                    )
                }
            }
        }
    }
}

@Composable
private fun AdvancedOptions(
    state: QRCodeGenerationState,
    onSizeChange: (String) -> Unit,
    onQRCodeColorChange: (String) -> Unit,
    onBackgroundColorChange: (String) -> Unit,
    onQuietZoneChange: (String) -> Unit,
    onFormatChange: (ImageFormat) -> Unit
) {
    SizeInputs(
        modifier = Modifier
            .padding(top = 8.dp),
        size = state.size?.toString() ?: "",
        onValueChange = onSizeChange,
    )
    ColorInputs(
        modifier = Modifier
            .padding(top = 8.dp),
        qrCodeColorString = state.qrCodeColorString,
        qrCodeColor = state.qrCodeColor,
        showQrCodeColorError = state.showColorFieldError,
        backgroundColorString = state.backgroundColorString,
        backgroundColor = state.backgroundColor,
        showBackgroundColorError = state.showBackgroundColorFieldError,
        onQRCodeColorChange = onQRCodeColorChange,
        onBackgroundColorChange = onBackgroundColorChange
    )
    QuietZoneInput(
        modifier = Modifier
            .padding(top = 8.dp),
        quietZone = state.quietZone,
        onValueChange = onQuietZoneChange
    )
    FormatInput(
        modifier = Modifier
            .padding(vertical = 8.dp),
        format = state.format,
        onValueChange = onFormatChange
    )
}

@Composable
private fun SizeInputs(
    modifier: Modifier,
    size: String,
    onValueChange: (String) -> Unit
) {
    Row(modifier = modifier) {
        SizeInput(
            modifier = Modifier.weight(1f),
            size = size,
            titleRes = Res.string.width,
            iconRes = Res.drawable.ic_width,
            onValueChange = onValueChange
        )
        SizeInput(
            modifier = Modifier.weight(1f),
            size = size,
            titleRes = Res.string.height,
            iconRes = Res.drawable.ic_height,
            onValueChange = onValueChange
        )
    }
}

@Composable
private fun ColorInputs(
    modifier: Modifier,
    qrCodeColorString: String,
    qrCodeColor: Int?,
    showQrCodeColorError: Boolean,
    backgroundColorString: String,
    backgroundColor: Int?,
    showBackgroundColorError: Boolean,
    onQRCodeColorChange: (String) -> Unit,
    onBackgroundColorChange: (String) -> Unit
) {
    Row(
        modifier = modifier
    ) {
        ColorInput(
            modifier = Modifier
                .weight(1f),
            iconRes = Res.drawable.ic_qr_code,
            titleRes = Res.string.qr_code_color,
            colorString = qrCodeColorString,
            color = qrCodeColor,
            isError = showQrCodeColorError,
            onValueChange = onQRCodeColorChange
        )
        ColorInput(
            modifier = Modifier
                .weight(1f),
            iconRes = Res.drawable.ic_background_grid,
            titleRes = Res.string.background_color,
            colorString = backgroundColorString,
            color = backgroundColor,
            isError = showBackgroundColorError,
            onValueChange = onBackgroundColorChange
        )
    }
}

@Preview
@Composable
private fun QRCodeGenerationContentPreview() {
    val state = QRCodeGenerationState()
    QRCodeGenerationContent(
        state = state
    )
}

@Preview
@Composable
private fun QRCodeGenerationContentSuccessPreview() {
    val state = QRCodeGenerationState(
        data = "https://www.google.com/",
        success = true
    )
    QRCodeGenerationContent(
        state = state
    )
}

@Preview
@Composable
private fun QRCodeGenerationContentExpandedPreview() {
    val state = QRCodeGenerationState(
        data = "https://www.google.com/",
        qrCodeImageUrl = "https://www.1zoom.ru/big2/541/255095-Sepik.jpg",
        qrCodeColorString = "FF0000",
        qrCodeColor = 0xFF0000,
        backgroundColorString = "000000",
        backgroundColor = null,
        advancedOptionsExpanded = false
    )
    QRCodeGenerationContent(
        state = state
    )
}

@Preview
@Composable
private fun QRCodeGenerationContentExpandedSuccessPreview() {
    val state = QRCodeGenerationState(
        data = "https://www.google.com/",
        qrCodeColorString = "FF0000",
        qrCodeColor = 0xFF0000,
        backgroundColorString = "000000",
        backgroundColor = null,
        success = true,
        advancedOptionsExpanded = false
    )
    QRCodeGenerationContent(
        state = state
    )
}