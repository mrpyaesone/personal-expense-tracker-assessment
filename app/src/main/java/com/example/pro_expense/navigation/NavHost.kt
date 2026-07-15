package com.example.pro_expense.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.pro_expense.ui.expenselist.ExpenseListRoute

private const val TWEEN_DURATION = 300

@Composable
internal fun ProExpenseNavHost(
    navController: NavHostController,
    startDestination: Any = ExpenseListRoute,
    modifier: Modifier = Modifier
) {
    NavHost(
        modifier = modifier, navController = navController,
        startDestination = startDestination,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Start,
                tween(TWEEN_DURATION)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Start,
                tween(TWEEN_DURATION)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.End,
                tween(TWEEN_DURATION)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.End,
                tween(TWEEN_DURATION)
            )
        }
    ) {
        navGraph(navController)
    }
}