package com.example.pro_expense.domain.utils

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

/**
 * Created by pyae on 15/7/26
 */
fun Long.toFormattedDateTime(
    pattern: String = "MMM dd, yyyy"
): String {
    val formatter = DateTimeFormatter
        .ofPattern(pattern, Locale.getDefault())

    return Instant.ofEpochMilli(this)
        .atZone(ZoneId.systemDefault())
        .format(formatter)
}