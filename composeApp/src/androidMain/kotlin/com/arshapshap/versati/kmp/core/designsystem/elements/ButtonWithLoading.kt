package com.arshapshap.versati.kmp.core.designsystem.elements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.arshapshap.versati.kmp.core.designsystem.theme.ButtonHeight

@Composable
fun ButtonWithLoading(
    modifier: Modifier,
    onClick: () -> Unit,
    text: String,
    loading: Boolean,
    textStyle: TextStyle? = null,
    textFontWeight: FontWeight? = null,
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(ButtonHeight),
        enabled = !loading
    ) {
        if (loading)
            Spacer(Modifier.weight(2f))
        Text(
            text = text,
            style = textStyle ?: LocalTextStyle.current,
            fontWeight = textFontWeight
        )
        if (loading) {
            Spacer(Modifier.weight(1f))
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterEnd
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ButtonWithLoadingPreview() {
    ButtonWithLoading(
        modifier = Modifier.fillMaxWidth(),
        onClick = { /*TODO*/ },
        text = "Button",
        loading = true
    )
}