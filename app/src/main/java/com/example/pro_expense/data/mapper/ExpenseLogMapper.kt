package com.example.pro_expense.data.mapper

import com.example.pro_expense.data.local.entities.ExpenseLogEntity
import com.example.pro_expense.domain.model.ExpenseLog
import com.example.pro_expense.domain.model.ExpenseLogCategory

/**
 * Created by pyae on 15/7/26
 */
fun ExpenseLog.toEntity() = ExpenseLogEntity(
    title = title, amount = amount, category = category.name, date = date, note = note
)

@JvmName("toEntityList")
fun List<ExpenseLog>.toEntity() = map(ExpenseLog::toEntity)

fun ExpenseLogEntity.toModel() = ExpenseLog(id = id,
    title = title, amount = amount,
    category = runCatching { ExpenseLogCategory.valueOf(category) }.getOrDefault(ExpenseLogCategory.OTHER),
    date = date, note = note
)

@JvmName("toModelList")
fun List<ExpenseLogEntity>.toModel() = map(ExpenseLogEntity::toModel)