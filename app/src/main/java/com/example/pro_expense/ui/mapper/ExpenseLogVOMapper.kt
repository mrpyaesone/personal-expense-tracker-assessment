package com.example.pro_expense.ui.mapper

import com.example.pro_expense.R
import com.example.pro_expense.domain.model.ExpenseLog
import com.example.pro_expense.domain.model.ExpenseLogCategory
import com.example.pro_expense.ui.vo.ExpenseLogVO

/**
 * Created by pyae on 15/7/26
 */
fun ExpenseLog.toVO() = ExpenseLogVO(id = id,
    icon = category.getIcon(), title = title, amount = "$amount",
    date = date,
    isExpense = category != ExpenseLogCategory.INCOME
)

@JvmName("toEntityList")
fun List<ExpenseLog>.toVO() = map(ExpenseLog::toVO)

fun ExpenseLogCategory.getIcon() = when (this) {
    ExpenseLogCategory.FOOD -> R.drawable.ic_food
    ExpenseLogCategory.TRANSPORT -> R.drawable.ic_transport
    ExpenseLogCategory.INCOME -> R.drawable.ic_income
    ExpenseLogCategory.SHOPPING -> R.drawable.ic_shopping
    ExpenseLogCategory.BILLS -> R.drawable.ic_bill
    ExpenseLogCategory.ENTERTAINMENT -> R.drawable.ic_entertainment
    ExpenseLogCategory.OTHER -> R.drawable.ic_other
}