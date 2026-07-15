package com.example.pro_expense.data.local.di

import com.example.pro_expense.data.local.ProExpenseDatabase
import com.example.pro_expense.data.local.dao.ExpenseLogDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DaoModule {
    @Provides
    @Singleton
    fun provideExpenseLogDao(database: ProExpenseDatabase): ExpenseLogDao =
        database.translationDao()
}