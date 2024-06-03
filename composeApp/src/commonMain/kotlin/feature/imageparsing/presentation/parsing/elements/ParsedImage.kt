package feature.imageparsing.presentation.parsing.elements

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import feature.imageparsing.domain.model.ParsingResult

@Composable
internal fun ParsedImage(
    modifier: Modifier,
    parsingResult: ParsingResult
) {
    // TODO: отображать пдфку
//    val pdfState = rememberHorizontalPdfReaderState(
//        resource = ResourceType.Remote(parsingResult.searchablePDFURL)
//    )
//    Box {
//        HorizontalPDFReader(
//            state = pdfState,
//            modifier = modifier
//                .background(MaterialTheme.colorScheme.background)
//        )
//        if (parsingResult.parsedResults.size > 1) {
//            Row(modifier = Modifier.padding(16.dp)) {
//                if (pdfState.currentPage != 0)
//                    Icon(
//                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
//                        contentDescription = "You can scroll to the left",
//                        tint = Color.Black
//                    )
//                Spacer(modifier = Modifier.weight(1f))
//                if (pdfState.currentPage != parsingResult.parsedResults.size - 1)
//                    Icon(
//                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
//                        contentDescription = "You can scroll to the right",
//                        tint = Color.Black
//                    )
//            }
//        }
//    }
}