package com.example.pro_expense.ui.expenselist

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

/**
 * Created by pyae on 15/7/26
 */
@Serializable
internal data object ExpenseListRoute

internal fun NavGraphBuilder.expenseListDestination(
    navController: NavHostController, onNavToLogExpense: () -> Unit, onNavToBack: () -> Unit,
) {
    composable<ExpenseListRoute> {
        ExpenseListScreen(
            canNavigateBack = navController.previousBackStackEntry != null,
            onNavBack = onNavToBack, onNavToLogExpense = onNavToLogExpense
        )
    }
}

fun NavHostController.navToExpenseList() {
    navigate(ExpenseListRoute)
}