package com.example.pro_expense.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.pro_expense.ui.theme.PreviewForBothTheme
import com.example.pro_expense.ui.theme.ProexpenseTheme

@Composable
fun defineButtonColor(
    enableBgColor: Color, disableBgColor: Color, enableTxtColor: Color, disableTxtColor: Color
): ButtonColors = ButtonDefaults.buttonColors(
    containerColor = enableBgColor, contentColor = enableTxtColor,
    disabledContainerColor = disableBgColor, disabledContentColor = disableTxtColor,
)

@Composable
fun RectangularButton(
    text: String, textStyle: TextStyle = TextStyle.Default,
    enable: Boolean = false, onClick: () -> Unit,
    enableBgColor: Color, disableBgColor: Color = Color.Gray,
    enableTxtColor: Color, disableTxtColor: Color = Color.Black,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier.requiredHeightIn(min = 48.dp),
        enabled = enable,
        colors = defineButtonColor(
            enableBgColor = enableBgColor,
            disableBgColor = disableBgColor,
            enableTxtColor = enableTxtColor,
            disableTxtColor = disableTxtColor
        ),
        shape = RoundedCornerShape(12),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)
    ) {
        Text(
            text, style = textStyle, textAlign = TextAlign.Center,
            maxLines = Int.MAX_VALUE,
            modifier = Modifier.padding(4.dp)
        )
    }
}

@PreviewForBothTheme
@Composable
private fun RectangularButtonPreview() {
    ProexpenseTheme {
        Surface {
            RectangularButton(
                text = "Continue",
                enable = true,
                onClick = {},
                enableBgColor = MaterialTheme.colorScheme.primary,
                enableTxtColor = Color.White,
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}
