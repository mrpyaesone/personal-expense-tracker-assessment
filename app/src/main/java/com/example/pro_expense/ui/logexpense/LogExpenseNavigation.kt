package com.example.pro_expense.ui.logexpense

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

/**
 * Created by pyae on 15/7/26
 */
@Serializable
internal data object LogExpenseRoute

internal fun NavGraphBuilder.logExpenseDestination(
    navController: NavHostController, onNavToHome: () -> Unit, onNavToBack: () -> Unit,
) {
    composable<LogExpenseRoute> {
        LogExpenseScreen(
            canNavigateBack = navController.previousBackStackEntry != null, onNavBack = onNavToBack
        )
    }
}

fun NavHostController.navToLogExpense() {
    navigate(LogExpenseRoute)
}