package com.example.pro_expense.domain.usecase

import com.example.pro_expense.domain.dispatcher.DispatcherProvider
import com.example.pro_expense.domain.model.ExpenseLog
import com.example.pro_expense.domain.repo.ExpenseRepo
import jakarta.inject.Singleton
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by pyae on 15/7/26
 */
@Singleton
class LogExpenseUseCase @Inject constructor(
    private val expenseLogRepo: ExpenseRepo, private val dispatcherProvider: DispatcherProvider
) {
    suspend operator fun invoke(expenseLog: ExpenseLog): Result<Unit> = withContext(dispatcherProvider.io) {
        try{
            expenseLogRepo.logExpense(expenseLog)
            Result.success(Unit)
        }catch (e:Exception){
            Result.failure(Exception("Error in saving expense log : ${e.message}"))
        }
    }
}