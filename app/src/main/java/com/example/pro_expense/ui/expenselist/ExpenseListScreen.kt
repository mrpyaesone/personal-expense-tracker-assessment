package com.example.pro_expense.ui.expenselist

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pro_expense.R
import com.example.pro_expense.domain.model.ExpenseLogCategory
import com.example.pro_expense.ui.components.AppBar
import com.example.pro_expense.ui.components.ExpenseCategoriesView
import com.example.pro_expense.ui.theme.PreviewForBothTheme
import com.example.pro_expense.ui.theme.ProexpenseTheme
import com.example.pro_expense.ui.vo.ExpenseLogVO
import com.example.pro_expense.ui.vo.SelectableItem

/**
 * Created by pyae on 15/7/26
 */
@Composable
internal fun ExpenseListScreen(
    canNavigateBack: Boolean, onNavBack: () -> Unit, onNavToLogExpense: () -> Unit,
    viewModel: ExpenseListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ExpenseListContent(
        uiState = uiState, canNavigateBack = canNavigateBack, onNavBack = onNavBack,
        onNavToLogExpense = onNavToLogExpense, onSelectedCategory = viewModel::onCategorySelected
    )
}

@Composable
private fun ExpenseListContent(
    uiState: ExpenseListUiState, canNavigateBack: Boolean, onNavBack: () -> Unit,
    onNavToLogExpense: () -> Unit, onSelectedCategory: (ExpenseLogCategory?) -> Unit,
    modifier: Modifier = Modifier.fillMaxSize()
) {
    Scaffold(topBar = {
        AppBar(
            title = stringResource(R.string.expense_list),
            canNavBack = canNavigateBack,
            navigateUp = {})
    }, floatingActionButton = {
        FloatingActionButton(
            onClick = onNavToLogExpense,
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.secondary
        ) {
            Icon(Icons.Filled.Add, "Log your expense")
        }
    }) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            ExpenseCategoriesView(
                categoryList = uiState.categoryList.map { it.value },
                selectedCategory = uiState.selectedCategory, onSelectedCategory = onSelectedCategory
            )

            if (uiState.expenseLogsList.isEmpty()) {
                Spacer(modifier = Modifier.weight(1f))
                EmptyContent(
                    noExpenseLogLabel = R.string.no_expense_log,
                    noExpenseLogIconRes = R.drawable.ic_add,)
                Spacer(modifier = Modifier.weight(1f))
            } else {
                ExpenseLogListContent(expenseLogs = uiState.expenseLogsList)
            }
        }
    }
}

@Composable
private fun EmptyContent(
    @StringRes noExpenseLogLabel: Int, @DrawableRes noExpenseLogIconRes: Int
) {
    Icon(
        painter = painterResource(id = noExpenseLogIconRes),
        tint = MaterialTheme.colorScheme.secondary,
        contentDescription = stringResource(R.string.expense_list),
        modifier = Modifier.size(96.dp)
    )
    Text(stringResource(id = noExpenseLogLabel), style = typography.bodyMedium)
}

@Composable
private fun ExpenseLogListContent(expenseLogs: List<ExpenseLogVO>) {
    LazyColumn(
        contentPadding = PaddingValues(top = 12.dp, bottom = 90.dp),
        modifier = Modifier.padding(top = 20.dp)
    ) {
        items(items = expenseLogs, key = { item -> item.hashCode() }) { item ->
            ExpenseLogItemView(item = item, onItemClick = {})
        }
    }
}

@PreviewForBothTheme
@Composable
private fun ExpenseListPreview() {
    ProexpenseTheme {
        Surface {
            ExpenseListContent(
                uiState = ExpenseListUiState(
                    categoryList = ExpenseLogCategory.entries.map {
                        SelectableItem(it.displayName, it)
                    }, selectedCategory = null,
                    expenseLogsList = emptyList(),
                    isEmpty = false, errorMsg = null
                ),
                canNavigateBack = true,
                onNavBack = {}, onSelectedCategory = {}, onNavToLogExpense = {})
        }
    }
}