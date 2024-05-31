package core.designsystem.elements

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.github.skeptick.libres.images.Image

@Composable
fun ImageWithLoading(
    modifier: Modifier,
    imageUrl: String,
    onSuccess: (Image?) -> Unit,
    onError: () -> Unit,
    placeholderIcon: @Composable () -> Unit,
    errorIcon: @Composable () -> Unit,
) {
    // TODO: добавить загрузку картинки
//    SubcomposeAsyncImage(
//        model = ImageRequest.Builder(LocalContext.current)
//            .data(imageUrl)
//            .build(),
//        contentDescription = null,
//        modifier = modifier
//    ) {
//        when {
//            imageUrl.isEmpty() -> placeholderIcon.invoke()
//            painter.state is AsyncImagePainter.State.Loading -> LoadingIndicator()
//            painter.state is AsyncImagePainter.State.Error -> {
//                onError()
//                errorIcon.invoke()
//            }
//
//            painter.state is AsyncImagePainter.State.Success -> {
//                onSuccess(painter.state.getBitmap())
//                SubcomposeAsyncImageContent()
//            }
//        }
//    }
}

//private fun AsyncImagePainter.State.getBitmap() =
//    (this as AsyncImagePainter.State.Success).result.drawable.toBitmapOrNull()

@Composable
private fun LoadingIndicator() {
    Box {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
    }
}