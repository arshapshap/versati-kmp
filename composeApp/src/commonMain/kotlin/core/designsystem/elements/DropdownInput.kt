package core.designsystem.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun DropdownInput(
    modifier: Modifier,
    valueString: String,
    onSelect: (String) -> Unit,
    entries: List<String>,
    leadingIcon: @Composable () -> Unit,
    label: String
) {
    var expanded by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = valueString,
        leadingIcon = leadingIcon,
        modifier = modifier
            .fillMaxWidth()
            .clickable { expanded = true },
        singleLine = true,
        label = { Text(text = label) },
        onValueChange = { },
        enabled = false,
        colors = getDefaultOutlinedTextInputColors()
    )
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        for (entry in entries) {
            DropdownMenuItem(
                text = { Text(entry) },
                onClick = {
                    onSelect(entry)
                    expanded = false
                }
            )
        }
    }
}

@Composable
private fun getDefaultOutlinedTextInputColors() = OutlinedTextFieldDefaults.colors(
    disabledTextColor = MaterialTheme.colorScheme.onSurface,
    disabledContainerColor = Color.Transparent,
    disabledBorderColor = MaterialTheme.colorScheme.outline,
    disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
    disabledTrailingIconColor = MaterialTheme.colorScheme.onSurface,
    disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
    disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
    disabledSupportingTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
    disabledPrefixColor = MaterialTheme.colorScheme.onSurfaceVariant,
    disabledSuffixColor = MaterialTheme.colorScheme.onSurfaceVariant
)