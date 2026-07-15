package com.example.pro_expense.ui.logexpense

import app.cash.turbine.test
import com.example.pro_expense.MainDispatcherRule
import com.example.pro_expense.domain.usecase.LogExpenseUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LogExpenseViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var logExpenseUseCase: LogExpenseUseCase
    private lateinit var viewModel: LogExpenseViewModel

    @Before
    fun setUp() {
        logExpenseUseCase = mockk()
        viewModel = LogExpenseViewModel(logExpenseUseCase)
    }

    @Test
    fun `updateExpenseTitle updates uiState and validation`() = runTest {
        viewModel.updateExpenseTitle("Coffee")
        assertEquals("Coffee", viewModel.uiState.value.expenseTitle)
        assertTrue(viewModel.uiState.value.isValidTitle)
    }

    @Test
    fun `updateExpenseAmount updates uiState and validation`() = runTest {
        viewModel.updateExpenseAmount("10.5")
        assertEquals("10.5", viewModel.uiState.value.expenseAmount)
        assertTrue(viewModel.uiState.value.isValidAmount)
    }

    @Test
    fun `logExpense emits Success event when usecase succeeds`() = runTest {
        viewModel.updateExpenseTitle("Lunch")
        viewModel.updateExpenseAmount("15.0")
        coEvery { logExpenseUseCase(any()) } returns Result.success(Unit)

        viewModel.logExpenseSaveEvent.test {
            viewModel.logExpense()
            assertEquals(LogExpenseSaveEvent.Success, awaitItem())
        }
    }

    @Test
    fun `logExpense emits Error event when usecase fails`() = runTest {
        viewModel.updateExpenseTitle("Lunch")
        viewModel.updateExpenseAmount("15.0")
        coEvery { logExpenseUseCase(any()) } returns Result.failure(Exception("Network Error"))

        viewModel.logExpenseSaveEvent.test {
            viewModel.logExpense()
            val event = awaitItem()
            assertTrue(event is LogExpenseSaveEvent.Error)
            assertEquals("Network Error", (event as LogExpenseSaveEvent.Error).message)
        }
    }
}
