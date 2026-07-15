package com.example.pro_expense.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.pro_expense.ui.expenselist.expenseListDestination
import com.example.pro_expense.ui.logexpense.logExpenseDestination
import com.example.pro_expense.ui.logexpense.navToLogExpense

internal fun NavGraphBuilder.navGraph(
    navController: NavHostController
) {
    expenseListDestination(
        navController, onNavToBack = navController::navigateUp,
        onNavToLogExpense = navController::navToLogExpense
    )

    logExpenseDestination(
        navController, onNavToBack = navController::navigateUp,
        onNavToHome = { navController.popBackStack() }
    )
}