package com.example.pro_expense.ui.expenselist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pro_expense.domain.model.ExpenseLogCategory
import com.example.pro_expense.domain.usecase.LoadExpenseLogsUseCase
import com.example.pro_expense.ui.mapper.toVO
import com.example.pro_expense.ui.vo.SelectableItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

/**
 * Created by pyae on 15/7/26
 */
@HiltViewModel
class ExpenseListViewModel @Inject constructor(private val loadExpenseLogsUseCase: LoadExpenseLogsUseCase) :
    ViewModel() {
    private val _expenseCategoryList = MutableStateFlow(
        listOf(SelectableItem<ExpenseLogCategory>("All", null))
                + ExpenseLogCategory.entries.map { SelectableItem(it.displayName, it) })

    private var _selectedCategory = MutableStateFlow<ExpenseLogCategory?>(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    private var _expenseList = _selectedCategory.flatMapLatest { category ->
            loadExpenseLogsUseCase(category)
        }

    val uiState: StateFlow<ExpenseListUiState> = combine(
        _expenseCategoryList, _expenseList, _selectedCategory
    ) { categoryList, expenseLogList, selectedCategory ->
        ExpenseListUiState(
            categoryList = categoryList, selectedCategory = selectedCategory,
            expenseLogsList = expenseLogList.toVO(), errorMsg = null
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ExpenseListUiState(
            categoryList = emptyList(),
            selectedCategory = null,
            expenseLogsList = emptyList(),
            errorMsg = null
        )
    )

    fun onCategorySelected(category: ExpenseLogCategory?) {
        _selectedCategory.value = category
    }
}