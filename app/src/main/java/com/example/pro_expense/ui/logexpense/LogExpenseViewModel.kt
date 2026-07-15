package com.example.pro_expense.ui.logexpense

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pro_expense.domain.model.ExpenseLog
import com.example.pro_expense.domain.model.ExpenseLogCategory
import com.example.pro_expense.domain.usecase.LogExpenseUseCase
import com.example.pro_expense.domain.utils.toFormattedDateTime
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Created by pyae on 15/7/26
 */
@HiltViewModel
class LogExpenseViewModel @Inject constructor(private val logExpenseUseCase: LogExpenseUseCase) :
    ViewModel() {

    private var _uiState = MutableStateFlow(
        LogExpenseUiState(
            expenseTitle = "", expenseAmount = "",
            isValidTitle = false, isValidAmount = false,
            selectedCategory = ExpenseLogCategory.FOOD,
            date = System.currentTimeMillis().toFormattedDateTime(),
            expenseNote = ""
        )
    )
    val uiState: StateFlow<LogExpenseUiState> = _uiState

    private var _logExpenseSaveEvent = Channel<LogExpenseSaveEvent>()
    val logExpenseSaveEvent: Flow<LogExpenseSaveEvent> = _logExpenseSaveEvent.receiveAsFlow()

    fun logExpense() {
        viewModelScope.launch {
            logExpenseUseCase(
                expenseLog = ExpenseLog(
                    id = 0,
                    title = uiState.value.expenseTitle,
                    amount = uiState.value.expenseAmount.toDoubleOrNull() ?: 0.0,
                    category = uiState.value.selectedCategory,
                    note = uiState.value.expenseNote, date = uiState.value.date
                )
            ).onSuccess { _logExpenseSaveEvent.send(LogExpenseSaveEvent.Success) }
                .onFailure { _logExpenseSaveEvent.send(LogExpenseSaveEvent.Error(it.message.orEmpty())) }
        }
    }

    fun updateExpenseTitle(newTitle: String) {
        if (newTitle.length < 20) {
            _uiState.update {
                it.copy(expenseTitle = newTitle, isValidTitle = newTitle.isNotBlank())
            }
        }
    }

    fun updateExpenseAmount(newAmt: String) {
        if (newAmt.length < 8) {
            _uiState.update { it.copy(expenseAmount = newAmt, isValidAmount = newAmt.isNotBlank()) }
        }
    }

    fun updateDateTime(newDate: Long?){
        _uiState.update { it.copy(date = (newDate ?: System.currentTimeMillis()).toFormattedDateTime()) }
    }

    fun updateExpenseNote(newNote: String) {
        _uiState.update { it.copy(expenseNote = newNote) }
    }

    fun onSelectedCategory(newCategory: ExpenseLogCategory?) {
        _uiState.update { it.copy(selectedCategory = newCategory ?: ExpenseLogCategory.OTHER) }
    }
}