package com.example.pro_expense.domain.usecase

import com.example.pro_expense.TestDispatcherProvider
import com.example.pro_expense.domain.model.ExpenseLog
import com.example.pro_expense.domain.model.ExpenseLogCategory
import com.example.pro_expense.domain.repo.ExpenseRepo
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class LoadExpenseLogsUseCaseTest {

    private lateinit var expenseRepo: ExpenseRepo
    private lateinit var loadExpenseLogsUseCase: LoadExpenseLogsUseCase
    private val dispatcherProvider = TestDispatcherProvider()

    @Before
    fun setUp() {
        expenseRepo = mockk()
        loadExpenseLogsUseCase = LoadExpenseLogsUseCase(expenseRepo, dispatcherProvider)
    }

    @Test
    fun `invoke with null category returns all expenses`() = runTest {
        // Given
        val expenses = listOf(
            ExpenseLog(1, "Lunch", 10.0, ExpenseLogCategory.FOOD, null, "2023-10-27"),
            ExpenseLog(2, "Salary", 1000.0, ExpenseLogCategory.INCOME, null, "2023-10-27")
        )
        every { expenseRepo.getExpenseLogList() } returns flowOf(expenses)

        // When
        val result = loadExpenseLogsUseCase(null).first()

        // Then
        assertEquals(2, result.size)
    }

    @Test
    fun `invoke with category filters expenses`() = runTest {
        // Given
        val expenses = listOf(
            ExpenseLog(1, "Lunch", 10.0, ExpenseLogCategory.FOOD, null, "2023-10-27"),
            ExpenseLog(2, "Salary", 1000.0, ExpenseLogCategory.INCOME, null, "2023-10-27")
        )
        every { expenseRepo.getExpenseLogList() } returns flowOf(expenses)

        // When
        val result = loadExpenseLogsUseCase(ExpenseLogCategory.FOOD).first()

        // Then
        assertEquals(1, result.size)
        assertEquals(ExpenseLogCategory.FOOD, result[0].category)
    }
}
