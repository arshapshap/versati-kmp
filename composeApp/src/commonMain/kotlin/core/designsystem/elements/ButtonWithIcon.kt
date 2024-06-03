package core.designsystem.elements

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import core.designsystem.theme.ButtonHeight
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ButtonWithIcon(
    modifier: Modifier,
    onClick: () -> Unit,
    text: String,
    painter: Painter,
    textStyle: TextStyle? = null,
    textFontWeight: FontWeight? = null,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(ButtonHeight),
        enabled = enabled
    ) {
        Spacer(Modifier.weight(2f))
        Text(
            text = text,
            style = textStyle ?: LocalTextStyle.current,
            fontWeight = textFontWeight
        )
        Spacer(Modifier.weight(1f))
        Icon(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview
@Composable
private fun Preview() {
    ButtonWithIcon(
        modifier = Modifier.fillMaxWidth(),
        onClick = { },
        text = "Button",
        painter = rememberVectorPainter(Icons.AutoMirrored.Filled.ArrowForward)
    )
}