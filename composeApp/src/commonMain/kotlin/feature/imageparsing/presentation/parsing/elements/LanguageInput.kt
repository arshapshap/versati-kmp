package feature.imageparsing.presentation.parsing.elements

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import core.designsystem.elements.DropdownInput
import feature.imageparsing.domain.model.Language
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import versati.composeapp.generated.resources.Res
import versati.composeapp.generated.resources.ic_language
import versati.composeapp.generated.resources.language

@Composable
internal fun LanguageInput(
    modifier: Modifier,
    language: Language,
    onValueChange: (Language) -> Unit
) {
    DropdownInput(
        modifier = modifier,
        valueString = language.name,
        onSelect = {
            onValueChange(Language.valueOf(it))
        },
        entries = Language.entries.map { it.name },
        leadingIcon = {
            Icon(
                painter = painterResource(Res.drawable.ic_language),
                tint = MaterialTheme.colorScheme.outline,
                contentDescription = null
            )
        },
        label = stringResource(Res.string.language)
    )
}

@Preview
@Composable
private fun FormatInputPreview() {
    LanguageInput(
        modifier = Modifier,
        language = Language.English,
        onValueChange = { }
    )
}