package com.example.pro_expense.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pro_expense.data.local.entities.ExpenseLogEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseLogDao {
    @Query("SELECT * FROM expense_log")
    fun getAllExpenseLogs(): Flow<List<ExpenseLogEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpenseLog(translations: ExpenseLogEntity)

    @Query("DELETE FROM expense_log")
    suspend fun deleteAllExpenseLogs()
}