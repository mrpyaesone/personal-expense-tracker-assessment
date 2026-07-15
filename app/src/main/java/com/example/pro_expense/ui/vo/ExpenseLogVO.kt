package com.example.pro_expense.ui.vo

import androidx.annotation.DrawableRes

/**
 * Created by pyae on 15/7/26
 */
data class ExpenseLogVO(val id:Int,
    @DrawableRes val icon: Int, val title: String, val amount: String, val date: String,
    val isExpense: Boolean
)
