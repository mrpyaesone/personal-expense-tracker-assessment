package com.example.pro_expense.domain.model

/**
 * Created by pyae on 15/7/26
 */
data class ExpenseLog(
    val id: Int,
    val title: String,
    val amount: Double,
    val category: ExpenseLogCategory,
    val note: String?,
    val date: String
)

enum class ExpenseLogCategory(val displayName: String) {
    FOOD("Food"),
    TRANSPORT("Transport"),
    INCOME("Income"),
    SHOPPING("Shopping"),
    BILLS("Bills"),
    ENTERTAINMENT("Entertainment"),
    OTHER("Other"),
}