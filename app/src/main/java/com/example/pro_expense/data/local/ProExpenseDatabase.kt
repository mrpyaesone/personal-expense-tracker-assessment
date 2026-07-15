package com.example.pro_expense.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pro_expense.data.local.dao.ExpenseLogDao
import com.example.pro_expense.data.local.entities.ExpenseLogEntity

@Database(entities = [ExpenseLogEntity::class], version = 1, exportSchema = false)
internal abstract class ProExpenseDatabase : RoomDatabase() {
    abstract fun translationDao(): ExpenseLogDao
}