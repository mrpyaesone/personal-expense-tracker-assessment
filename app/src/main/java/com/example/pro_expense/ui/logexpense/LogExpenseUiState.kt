package com.example.pro_expense.ui.logexpense

import com.example.pro_expense.domain.model.ExpenseLogCategory

/**
 * Created by pyae on 15/7/26
 */

data class LogExpenseUiState(
    val expenseTitle: String, val isValidTitle: Boolean,
    val expenseAmount: String, val isValidAmount: Boolean,
    val selectedCategory: ExpenseLogCategory,
    val expenseNote: String?, val date: String
) {
    val isSaveEnabled: Boolean
        get() = (isValidTitle && isValidAmount)
}

sealed class LogExpenseSaveEvent {
    data object Idle : LogExpenseSaveEvent()
    data object Success : LogExpenseSaveEvent()
    data class Error(val message: String) : LogExpenseSaveEvent()
}