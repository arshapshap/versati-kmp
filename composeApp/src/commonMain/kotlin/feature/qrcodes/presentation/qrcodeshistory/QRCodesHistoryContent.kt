package feature.qrcodes.presentation.qrcodeshistory

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import core.designsystem.elements.ConfirmationAlertDialog
import feature.qrcodes.domain.model.ImageFormat
import feature.qrcodes.domain.model.QRCodeInfo
import feature.qrcodes.presentation.common.ui.QRCodeImage
import feature.qrcodes.presentation.qrcodeshistory.contract.QRCodesHistoryState
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import versati.composeapp.generated.resources.Res
import versati.composeapp.generated.resources.clearing_the_history
import versati.composeapp.generated.resources.do_you_really_want_to_clear_the_history
import versati.composeapp.generated.resources.the_history_of_qr_codes_is_empty

@Composable
internal fun QRCodesHistoryContent(
    state: QRCodesHistoryState,
    viewModel: QRCodesHistoryViewModel
) {
    QRCodesHistoryContent(
        state = state,
        onQRCodeClick = viewModel::openQRCode,
        onClearHistory = viewModel::clearHistoryConfirmed,
        onCancelClearing = viewModel::cancelClear
    )
}

@Composable
private fun QRCodesHistoryContent(
    state: QRCodesHistoryState,
    onQRCodeClick: (Long) -> Unit = { },
    onClearHistory: () -> Unit = { },
    onCancelClearing: () -> Unit = { }
) {
    if (state.showDialogToConfirmClear) {
        ConfirmationAlertDialog(
            onDismissRequest = { onCancelClearing() },
            onConfirmation = { onClearHistory() },
            dialogTitle = stringResource(Res.string.clearing_the_history),
            dialogText = stringResource(Res.string.do_you_really_want_to_clear_the_history),
            icon = Icons.Default.Delete
        )
    }
    if (state.history.isEmpty()) {
        Text(
            text = stringResource(Res.string.the_history_of_qr_codes_is_empty),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )
    } else {
        LazyColumn {
            items(state.history) {
                QRCodeInfo(
                    qrCodeInfo = it,
                    onClick = onQRCodeClick
                )
            }
        }
    }
}

@Composable
private fun QRCodeInfo(
    qrCodeInfo: QRCodeInfo,
    onClick: (Long) -> Unit
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick(qrCodeInfo.id) }
        .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .height(70.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = qrCodeInfo.data,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(end = 16.dp),
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium
            )
            QRCodeImage(
                modifier = Modifier
                    .fillMaxHeight(),
                imageUrl = qrCodeInfo.imageUrl
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    val state = QRCodesHistoryState(
        List(5) {
            QRCodeInfo(
                id = 1,
                data = "google.com",
                size = 200,
                color = 0x000000,
                backgroundColor = 0xffffff,
                quietZone = 1,
                format = ImageFormat.PNG,
                imageUrl = "qrcodeurl"
            )
        }
    )
    QRCodesHistoryContent(
        state = state,
        onQRCodeClick = { }
    )
}

@Preview
@Composable
private fun HistoryClearPreview() {
    val state = QRCodesHistoryState(
        listOf()
    )
    QRCodesHistoryContent(
        state = state,
        onQRCodeClick = { }
    )
}