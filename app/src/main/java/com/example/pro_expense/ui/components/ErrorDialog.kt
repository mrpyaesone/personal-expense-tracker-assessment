package com.example.pro_expense.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.example.pro_expense.ui.theme.PreviewForBothTheme
import com.example.pro_expense.ui.theme.ProexpenseTheme

/**
 * Created by pyae on 15/7/26
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ErrorDialog(
    errMsg: String, onDismiss: () -> Unit, modifier: Modifier = Modifier
        .fillMaxWidth(0.8f)
        .wrapContentHeight()
) {
    BasicAlertDialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false),
        modifier = modifier
    ) {
        ErrorDialogContent(errMsg = errMsg, onDismiss = onDismiss, modifier = modifier)
    }
}

@Composable
private fun ErrorDialogContent(errMsg: String, onDismiss: () -> Unit, modifier: Modifier) {
    Surface(modifier = modifier, shape = MaterialTheme.shapes.medium) {
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Icon(
                imageVector = Icons.Filled.Warning, tint = colorScheme.error,
                contentDescription = "Back", modifier = Modifier
                    .size(120.dp)
                    .padding(20.dp)
            )

            Text(errMsg, style = typography.bodyMedium, textAlign = TextAlign.Center)

            RectangularButton(
                text = "ok", textStyle = typography.bodyMedium, onClick = onDismiss, enable = true,
                enableBgColor = colorScheme.primary, enableTxtColor = colorScheme.onPrimary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp)
            )
        }
    }
}

@PreviewForBothTheme
@Composable
private fun ErrorDialogPreview() {
    ProexpenseTheme {
        Surface {
            ErrorDialogContent(
                errMsg = "Error message",
                onDismiss = {},
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .wrapContentHeight()
            )
        }
    }
}