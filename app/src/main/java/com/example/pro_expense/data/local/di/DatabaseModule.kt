package com.example.pro_expense.data.local.di

import android.content.Context
import androidx.room.Room
import com.example.pro_expense.data.local.ProExpenseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ProExpenseDatabase =
        Room.databaseBuilder(context = context, ProExpenseDatabase::class.java, "pro_expense.db")
            .build()
}