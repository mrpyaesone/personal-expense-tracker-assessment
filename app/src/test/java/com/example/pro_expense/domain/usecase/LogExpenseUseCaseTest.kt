package com.example.pro_expense.domain.usecase

import com.example.pro_expense.TestDispatcherProvider
import com.example.pro_expense.domain.model.ExpenseLog
import com.example.pro_expense.domain.model.ExpenseLogCategory
import com.example.pro_expense.domain.repo.ExpenseRepo
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class LogExpenseUseCaseTest {

    private lateinit var expenseRepo: ExpenseRepo
    private lateinit var logExpenseUseCase: LogExpenseUseCase
    private val dispatcherProvider = TestDispatcherProvider()

    @Before
    fun setUp() {
        expenseRepo = mockk()
        logExpenseUseCase = LogExpenseUseCase(expenseRepo, dispatcherProvider)
    }

    @Test
    fun `invoke calls repo logExpense and returns success`() = runTest {
        // Given
        val expenseLog = ExpenseLog(0, "Coffee", 5.0, ExpenseLogCategory.FOOD, "Note", "2023-10-27")
        coEvery { expenseRepo.logExpense(expenseLog) } returns Unit

        // When
        val result = logExpenseUseCase(expenseLog)

        // Then
        assertTrue(result.isSuccess)
        coVerify { expenseRepo.logExpense(expenseLog) }
    }

    @Test
    fun `invoke returns failure when repo throws exception`() = runTest {
        // Given
        val expenseLog = ExpenseLog(0, "Coffee", 5.0, ExpenseLogCategory.FOOD, "Note", "2023-10-27")
        coEvery { expenseRepo.logExpense(expenseLog) } throws Exception("DB Error")

        // When
        val result = logExpenseUseCase(expenseLog)

        // Then
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull()?.message?.contains("Error in saving expense log") == true)
    }
}
