package com.example.pro_expense

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by pyae on 15/7/26
 */

@HiltAndroidApp
class ProExpenseApplication: Application(){
    override fun onCreate() {
        super.onCreate()
    }
}