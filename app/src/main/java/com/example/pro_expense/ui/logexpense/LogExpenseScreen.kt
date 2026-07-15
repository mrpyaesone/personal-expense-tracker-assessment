package com.example.pro_expense.ui.logexpense

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pro_expense.R
import com.example.pro_expense.domain.model.ExpenseLogCategory
import com.example.pro_expense.domain.utils.toFormattedDateTime
import com.example.pro_expense.ui.components.AppBar
import com.example.pro_expense.ui.components.DatePickerModal
import com.example.pro_expense.ui.components.ErrorDialog
import com.example.pro_expense.ui.components.ExpenseCategoriesView
import com.example.pro_expense.ui.components.InputTextField
import com.example.pro_expense.ui.components.RectangularButton
import com.example.pro_expense.ui.theme.PreviewForBothTheme
import com.example.pro_expense.ui.theme.ProexpenseTheme

/**
 * Created by pyae on 15/7/26
 */
@Composable
internal fun LogExpenseScreen(
    canNavigateBack: Boolean, onNavBack: () -> Unit,
    viewModel: LogExpenseViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var errorMessage by rememberSaveable { mutableStateOf<String?>(null) }
    errorMessage?.let {
        ErrorDialog(errMsg = it, onDismiss = { errorMessage = null })
    }

    LaunchedEffect(Unit) {
        viewModel.logExpenseSaveEvent.collect {
            when (it) {
                LogExpenseSaveEvent.Success -> onNavBack()
                is LogExpenseSaveEvent.Error -> {
                    errorMessage = it.message
                }

                LogExpenseSaveEvent.Idle -> {}
            }
        }
    }

    LogExpenseContent(
        uiState = uiState,
        canNavigateBack = canNavigateBack, onNavBack = onNavBack,
        onTitleChange = viewModel::updateExpenseTitle,
        onAmountChange = viewModel::updateExpenseAmount,
        onNoteChange = viewModel::updateExpenseNote,
        onSelectedCategory = viewModel::onSelectedCategory,
        onDateChange = viewModel::updateDateTime,
        onSaveBtnClick = viewModel::logExpense
    )
}

@Composable
private fun LogExpenseContent(
    uiState: LogExpenseUiState,
    canNavigateBack: Boolean, onNavBack: () -> Unit, onTitleChange: (String) -> Unit = {},
    onAmountChange: (String) -> Unit = {}, onNoteChange: (String) -> Unit = {},
    onSelectedCategory: (ExpenseLogCategory?) -> Unit,
    onDateChange:(Long?)->Unit, onSaveBtnClick: () -> Unit,
    modifier: Modifier = Modifier.fillMaxSize()
) {
    val focusManager = LocalFocusManager.current
    var showDatePicker by rememberSaveable { mutableStateOf(false) }

    Scaffold(topBar = {
        AppBar(
            title = stringResource(R.string.log_expense),
            canNavBack = canNavigateBack,
            navigateUp = {
                IconButton(
                    onClick = onNavBack, modifier = Modifier
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary)
                        .size(36.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = "Back", tint = MaterialTheme.colorScheme.surface
                    )
                }
            })
    }) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .padding(20.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            InputTextField(
                inputText = uiState.expenseTitle, textChanges = onTitleChange,
                label = stringResource(R.string.expense_title), singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                ),
                keyboardActions = { focusManager.moveFocus(FocusDirection.Down) },
                modifier = Modifier
                    .fillMaxWidth()
            )

            InputTextField(
                inputText = uiState.expenseAmount, textChanges = onAmountChange,
                label = stringResource(R.string.expense_amount), singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number, imeAction = ImeAction.Done
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )
            ExpenseCategoriesView(
                categoryList = ExpenseLogCategory.entries,
                selectedCategory = uiState.selectedCategory, onSelectedCategory = onSelectedCategory
            )
            InputTextField(
                inputText = uiState.date,
                textChanges = { }, enabled = false,
                label = System.currentTimeMillis().toFormattedDateTime(),
                startIcon = R.drawable.ic_calendar,
                startIconAction = { showDatePicker = !showDatePicker },
                readOnly = true,
                disableBgColor = Color.Transparent,
                disableTxtColor = MaterialTheme.colorScheme.scrim,
                modifier = Modifier.padding(vertical = 8.dp)
                    .fillMaxWidth()
                    .clickable { showDatePicker = !showDatePicker }
            )
            if (showDatePicker) {
                DatePickerModal(
                    onDateSelected = onDateChange,
                    onDismiss = { showDatePicker = false })
            }

            InputTextField(
                inputText = uiState.expenseNote.orEmpty(),
                textChanges = onNoteChange,
                label = stringResource(R.string.expense_note),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text, imeAction = ImeAction.Done
                ),
                keyboardActions = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 200.dp, max = 400.dp)
                    .padding(vertical = 20.dp)
            )

            Spacer(modifier = Modifier.weight(1f))
            RectangularButton(
                text = stringResource(R.string.save),
                enable = uiState.isSaveEnabled,
                onClick = onSaveBtnClick,
                enableBgColor = MaterialTheme.colorScheme.primary,
                enableTxtColor = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp)
            )
        }
    }
}

@PreviewForBothTheme
@Composable
private fun LogExpensePreview() {
    ProexpenseTheme {
        Surface {
            LogExpenseContent(
                uiState = LogExpenseUiState(
                    expenseTitle = "title",
                    expenseAmount = "100",
                    isValidTitle = true,
                    isValidAmount = true,
                    selectedCategory = ExpenseLogCategory.TRANSPORT,
                    date = "",
                    expenseNote = "note",
                ),
                canNavigateBack = true,
                onNavBack = {},
                onTitleChange = {},
                onAmountChange = {},
                onNoteChange = {},
                onSelectedCategory = {},
                onDateChange = {},onSaveBtnClick = {})
        }
    }
}