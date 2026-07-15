package com.example.pro_expense.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pro_expense.ui.theme.PreviewForBothTheme
import com.example.pro_expense.ui.theme.ProexpenseTheme

@Composable
fun InputTextField(
    inputText: String,
    textChanges: (String) -> Unit,
    label: String? = null,
    placeholder: String? = null,
    supportingText: String? = null,
    @DrawableRes endIcon: Int? = null,
    endIconAction: () -> Unit = {},
    @DrawableRes startIcon: Int? = null,
    startIconAction: () -> Unit = {},
    modifier: Modifier = Modifier,
    textStyle: TextStyle = LocalTextStyle.current,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    isError: Boolean = false,
    singleLine: Boolean = false,
    maxLength: Int = Int.MAX_VALUE,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Default
    ),
    keyboardActions: () -> Unit = {},
    visualTransformation: VisualTransformation = VisualTransformation.None,
    bgColor: Color = Color.Transparent,
    focusTxtColor: Color = Color.Unspecified,
    disableBgColor: Color = Color.Unspecified,
    disableTxtColor: Color = Color.Unspecified,
    disableIconColor: Color = Color.Unspecified,
    errTxtColor: Color = Color.Unspecified,
    contextDescription: String? = null,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        value = inputText,
        onValueChange = textChanges,
        label = { label?.let { Text(label, modifier = Modifier.padding(bottom = 4.dp)) } },
        placeholder = { placeholder?.let { Text(placeholder) } },
        supportingText = { supportingText?.let { Text(supportingText) } },
        trailingIcon = endIcon?.let {
            {
                IconButton(onClick = endIconAction) {
                    Icon(
                        painter = painterResource(endIcon),
                        contentDescription = null
                    )
                }
            }
        },
        leadingIcon = if (startIcon != null) {
            {
                IconButton(onClick = startIconAction) {
                    Icon(painter = painterResource(startIcon), contentDescription = null)
                }
            }
        } else null,
        textStyle = textStyle,
        enabled = enabled,
        readOnly = readOnly,
        isError = isError,
        minLines = 1,
        maxLines = maxLines,
        keyboardOptions = keyboardOptions,
        keyboardActions = KeyboardActions {
            keyboardActions()
            keyboardController?.hide()
        },
        visualTransformation = visualTransformation,
        modifier = modifier,
        colors = TextFieldDefaults.colors(
            focusedTextColor = focusTxtColor,
            unfocusedContainerColor = bgColor,
            focusedContainerColor = bgColor,
            disabledContainerColor = disableBgColor,
            disabledTextColor = disableTxtColor,
            disabledLeadingIconColor = disableIconColor,
            disabledTrailingIconColor = disableIconColor,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.outlineVariant,
            unfocusedLabelColor = MaterialTheme.colorScheme.outline,
            unfocusedTrailingIconColor = MaterialTheme.colorScheme.outline
        )
    )
}

@PreviewForBothTheme
@Composable
private fun InputTextFieldPreview() {
    ProexpenseTheme {
        Surface {
            InputTextField(
                inputText = "",
                textChanges = { text -> print(text) },
                label = "User Name",
//        endIcon = ,
//        startIcon = R.drawable.ic_clear,
                modifier = Modifier.padding(20.dp)
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun InputTextAreaPreview() {
    InputTextField(
        inputText = "",
        textChanges = { text -> print(text) },
        label = "User Name",
//        endIcon = ,
//        startIcon = R.drawable.ic_clear,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(20.dp)
    )
}