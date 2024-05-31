package feature.qrcodes.presentation.common.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import core.designsystem.elements.ImageWithLoading
import io.github.skeptick.libres.images.Image
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import versati.composeapp.generated.resources.Res
import versati.composeapp.generated.resources.ic_qr_code
import versati.composeapp.generated.resources.uploading_qr_code_error

@Composable
internal fun QRCodeImage(
    modifier: Modifier,
    imageUrl: String,
    onSuccess: (Image?) -> Unit = { },
    onError: () -> Unit = { }
) {
    ImageWithLoading(
        modifier = modifier,
        imageUrl = imageUrl,
        onSuccess = onSuccess,
        onError = onError,
        placeholderIcon = {
            Icon(
                painter = painterResource(Res.drawable.ic_qr_code),
                contentDescription = null
            )
        },
        errorIcon = {
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = stringResource(Res.string.uploading_qr_code_error),
            )
        }
    )
}