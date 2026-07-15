package com.example.pro_expense.domain.di

import com.example.pro_expense.domain.dispatcher.DispatcherProvider
import com.example.pro_expense.domain.dispatcher.DispatcherProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DispatcherModule {
    @Binds
    @Singleton
    abstract fun provideDispatcherProvider(dispatcherProviderImpl: DispatcherProviderImpl): DispatcherProvider
}