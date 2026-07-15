package com.example.pro_expense.data

import com.example.pro_expense.data.local.dao.ExpenseLogDao
import com.example.pro_expense.data.local.entities.ExpenseLogEntity
import com.example.pro_expense.domain.model.ExpenseLog
import com.example.pro_expense.domain.model.ExpenseLogCategory
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ExpenseRepoImplTest {

    private lateinit var expenseDao: ExpenseLogDao
    private lateinit var expenseRepo: ExpenseRepoImpl

    @Before
    fun setUp() {
        expenseDao = mockk()
        expenseRepo = ExpenseRepoImpl(expenseDao)
    }

    @Test
    fun `getExpenseLogList returns mapped list from dao`() = runTest {
        // Given
        val entities = listOf(
            ExpenseLogEntity(1, "Lunch", 10.0, "FOOD", "2023-10-27", "Note")
        )
        every { expenseDao.getAllExpenseLogs() } returns flowOf(entities)

        // When
        val result = expenseRepo.getExpenseLogList().first()

        // Then
        assertEquals(1, result.size)
        assertEquals("Lunch", result[0].title)
        assertEquals(ExpenseLogCategory.FOOD, result[0].category)
    }

    @Test
    fun `logExpense calls dao insert`() = runTest {
        // Given
        val expenseLog = ExpenseLog(0, "Coffee", 5.0, ExpenseLogCategory.FOOD, "Note", "2023-10-27")
        coEvery { expenseDao.insertExpenseLog(any()) } returns Unit

        // When
        expenseRepo.logExpense(expenseLog)

        // Then
        coVerify { expenseDao.insertExpenseLog(match { 
            it.title == "Coffee" && it.amount == 5.0 && it.category == "FOOD"
        }) }
    }
}
