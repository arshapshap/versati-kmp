package core.designsystem.elements

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil3.compose.AsyncImagePainter
import coil3.compose.SubcomposeAsyncImage
import coil3.compose.SubcomposeAsyncImageContent

@Composable
fun ImageWithLoading(
    modifier: Modifier,
    imageUrl: String,
    onSuccess: () -> Unit,
    onError: () -> Unit,
    placeholderIcon: @Composable () -> Unit,
    errorIcon: @Composable () -> Unit,
) {
    SubcomposeAsyncImage(
        model = imageUrl,
        contentDescription = null,
        modifier = modifier
    ) {
        when {
            imageUrl.isEmpty() -> placeholderIcon.invoke()
            painter.state is AsyncImagePainter.State.Loading -> LoadingIndicator()
            painter.state is AsyncImagePainter.State.Error -> {
                onError()
                errorIcon.invoke()
            }

            painter.state is AsyncImagePainter.State.Success -> {
                onSuccess()
                SubcomposeAsyncImageContent()
            }
        }
    }
}

@Composable
private fun LoadingIndicator() {
    Box {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
    }
}