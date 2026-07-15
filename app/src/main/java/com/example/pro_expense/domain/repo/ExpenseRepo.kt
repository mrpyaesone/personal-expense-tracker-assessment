package com.example.pro_expense.domain.repo

import com.example.pro_expense.domain.model.ExpenseLog
import kotlinx.coroutines.flow.Flow

/**
 * Created by pyae on 15/7/26
 */
interface ExpenseRepo {
    suspend fun logExpense(expenseLog: ExpenseLog)
     fun getExpenseLogList(): Flow<List<ExpenseLog>>
}