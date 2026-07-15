package com.example.pro_expense.ui.expenselist

import app.cash.turbine.test
import com.example.pro_expense.MainDispatcherRule
import com.example.pro_expense.domain.model.ExpenseLog
import com.example.pro_expense.domain.model.ExpenseLogCategory
import com.example.pro_expense.domain.usecase.LoadExpenseLogsUseCase
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ExpenseListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var loadExpenseLogsUseCase: LoadExpenseLogsUseCase
    private lateinit var viewModel: ExpenseListViewModel

    @Before
    fun setUp() {
        loadExpenseLogsUseCase = mockk()
        // Initial call in ViewModel init
        every { loadExpenseLogsUseCase(null) } returns flowOf(emptyList())
        viewModel = ExpenseListViewModel(loadExpenseLogsUseCase)
    }

    @Test
    fun `uiState initially emits empty list and all categories`() = runTest {
        viewModel.uiState.test {
            val initialState = awaitItem()
            assertEquals(0, initialState.expenseLogsList.size)
            // "All" + ExpenseLogCategory.entries.size
            assertEquals(ExpenseLogCategory.entries.size + 1, initialState.categoryList.size)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onCategorySelected updates uiState and triggers usecase`() = runTest {
        val expenses = listOf(
            ExpenseLog(1, "Lunch", 10.0, ExpenseLogCategory.FOOD, null, "2023-10-27")
        )
        every { loadExpenseLogsUseCase(ExpenseLogCategory.FOOD) } returns flowOf(expenses)

        viewModel.uiState.test {
            // Initial state
            awaitItem()
            
            viewModel.onCategorySelected(ExpenseLogCategory.FOOD)
            
            // Collect items until we get the one with the expected category and non-empty list
            // or use expectMostRecentItem() if we just want the final state.
            // Using a loop to handle potential intermediate states due to combine + flatMapLatest
            var state = awaitItem()
            while (state.selectedCategory != ExpenseLogCategory.FOOD || state.expenseLogsList.isEmpty()) {
                state = awaitItem()
            }

            assertEquals(ExpenseLogCategory.FOOD, state.selectedCategory)
            assertEquals(1, state.expenseLogsList.size)
            assertEquals("Lunch", state.expenseLogsList[0].title)
        }
    }
}
