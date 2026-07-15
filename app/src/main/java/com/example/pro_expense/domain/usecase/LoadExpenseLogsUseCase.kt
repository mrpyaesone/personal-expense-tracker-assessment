package com.example.pro_expense.domain.usecase

import com.example.pro_expense.domain.dispatcher.DispatcherProvider
import com.example.pro_expense.domain.model.ExpenseLog
import com.example.pro_expense.domain.model.ExpenseLogCategory
import com.example.pro_expense.domain.repo.ExpenseRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by pyae on 15/7/26
 */
@Singleton
class LoadExpenseLogsUseCase @Inject constructor(
    private val expenseRepo: ExpenseRepo, private val dispatcherProvider: DispatcherProvider
) {
    operator fun invoke(category: ExpenseLogCategory?): Flow<List<ExpenseLog>> =
        expenseRepo.getExpenseLogList().map { expenses ->
            category?.let { selected ->
                expenses.filter { it.category == selected }
            } ?: expenses
        }.flowOn(dispatcherProvider.io)
}