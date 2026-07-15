package com.example.pro_expense.ui.expenselist

import com.example.pro_expense.domain.model.ExpenseLogCategory
import com.example.pro_expense.ui.vo.ExpenseLogVO
import com.example.pro_expense.ui.vo.SelectableItem

/**
 * Created by pyae on 15/7/26
 */
data class ExpenseListUiState(
    val categoryList: List<SelectableItem<ExpenseLogCategory>>,
    val selectedCategory: ExpenseLogCategory?, val expenseLogsList: List<ExpenseLogVO>,
    val isEmpty: Boolean = expenseLogsList.isEmpty(),
    val errorMsg: String?
)