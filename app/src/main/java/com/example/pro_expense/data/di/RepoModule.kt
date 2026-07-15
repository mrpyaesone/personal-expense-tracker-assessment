package com.example.pro_expense.data.di

import com.example.pro_expense.data.ExpenseRepoImpl
import com.example.pro_expense.domain.repo.ExpenseRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {
    @Binds
    @Singleton
    abstract fun provideExpenseRepo(expenseRepoImpl: ExpenseRepoImpl): ExpenseRepo
}