package feature.charts.presentation.common.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import core.designsystem.elements.ImageWithLoading
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import versati.composeapp.generated.resources.Res
import versati.composeapp.generated.resources.first_loading_hint
import versati.composeapp.generated.resources.ic_chart_finance
import versati.composeapp.generated.resources.uploading_chart_error

@Composable
internal fun ChartImage(
    modifier: Modifier,
    imageUrl: String,
    showHint: Boolean = false,
    onSuccess: () -> Unit = { },
    onError: () -> Unit = { }
) {
    Box(modifier = modifier) {
        if (showHint)
            Text(
                text = stringResource(Res.string.first_loading_hint),
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .fillMaxWidth()
            )
        ImageWithLoading(
            modifier = modifier,
            imageUrl = imageUrl,
            onSuccess = onSuccess,
            onError = onError,
            placeholderIcon = {
                Icon(
                    painter = painterResource(Res.drawable.ic_chart_finance),
                    contentDescription = null
                )
            },
            errorIcon = {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = stringResource(Res.string.uploading_chart_error),
                )
            }
        )
    }
}