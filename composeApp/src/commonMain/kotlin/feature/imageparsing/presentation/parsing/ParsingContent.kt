package feature.imageparsing.presentation.parsing

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import core.designsystem.elements.ButtonWithLoading
import feature.imageparsing.domain.model.Language
import feature.imageparsing.domain.model.ParsingResult
import feature.imageparsing.presentation.parsing.contract.ParsingState
import feature.imageparsing.presentation.parsing.elements.DataInput
import feature.imageparsing.presentation.parsing.elements.LanguageInput
import feature.imageparsing.presentation.parsing.elements.ParsedImage
import org.jetbrains.compose.resources.pluralStringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import versati.composeapp.generated.resources.Res
import versati.composeapp.generated.resources.parse_image
import versati.composeapp.generated.resources.parsed_images
import versati.composeapp.generated.resources.result

@Composable
internal fun ParsingContent(
    state: ParsingState,
    onUrlChange: (String) -> Unit = { },
    onLanguageChange: (Language) -> Unit = { },
    onParseClick: () -> Unit = { }
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        DataInput(
            modifier = Modifier
                .padding(vertical = 8.dp),
            text = state.url,
            onValueChange = onUrlChange,
            isError = state.showUrlFieldError
        )
        LanguageInput(
            modifier = Modifier
                .padding(vertical = 8.dp),
            language = state.language,
            onValueChange = onLanguageChange
        )
        ButtonWithLoading(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            onClick = onParseClick,
            text = stringResource(Res.string.parse_image),
            loading = state.loading,
            textStyle = MaterialTheme.typography.headlineSmall,
            textFontWeight = FontWeight.Bold
        )
        if (state.parsingResult != null)
            Result(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                result = state.parsingResult!!
            )
    }
}

@Composable
private fun Result(
    modifier: Modifier,
    result: ParsingResult
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(Res.string.result),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        SelectionContainer(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outlineVariant,
                    shape = MaterialTheme.shapes.extraSmall
                )
                .padding(8.dp)
        ) {
            Column {
                for (parsedImage in result.parsedResults) {
                    Text(text = parsedImage.parsedText)
                }
            }
        }
        Text(
            text = pluralStringResource(Res.plurals.parsed_images, result.parsedResults.size),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(top = 16.dp, bottom = 8.dp)
        )
        ParsedImage(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            parsingResult = result
        )
    }
}

@Preview
@Composable
private fun Preview() {
    val state = ParsingState(
        parsingResult = ParsingResult(
            id = 1,
            parsedResults = listOf(
                feature.imageparsing.domain.model.ParsedImage(
                    parsedText = "LLalalallLLalalallaalLLalalallaalLLalalallaalLLalalallaalaal"
                )
            ),
            searchablePDFURL = "asd",
            sourceUrl = "asd.com"
        ),
        loading = true
    )
    ParsingContent(state = state)
}