package com.example.pro_expense.data

import com.example.pro_expense.data.local.dao.ExpenseLogDao
import com.example.pro_expense.data.mapper.toEntity
import com.example.pro_expense.data.mapper.toModel
import com.example.pro_expense.domain.model.ExpenseLog
import com.example.pro_expense.domain.repo.ExpenseRepo
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created by pyae on 15/7/26
 */
class ExpenseRepoImpl @Inject constructor(private val expenseDao: ExpenseLogDao) : ExpenseRepo {
    override suspend fun logExpense(expenseLog: ExpenseLog) {
        expenseDao.insertExpenseLog(expenseLog.toEntity())
    }

    override fun getExpenseLogList(): Flow<List<ExpenseLog>> =
        expenseDao.getAllExpenseLogs().map { it.toModel() }
}