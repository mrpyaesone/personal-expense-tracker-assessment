package com.example.pro_expense.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pro_expense.domain.model.ExpenseLogCategory
import com.example.pro_expense.ui.theme.PreviewForBothTheme
import com.example.pro_expense.ui.theme.ProexpenseTheme

/**
 * Created by pyae on 15/7/26
 */
@Composable
fun ExpenseCategoriesView(
    categoryList: List<ExpenseLogCategory?>, selectedCategory: ExpenseLogCategory?,
    onSelectedCategory: (ExpenseLogCategory?) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.wrapContentSize()
    ) {
        items(categoryList) {
            FilterChip(
                selected = selectedCategory == it, label = { Text(it?.displayName ?: "All") },
                onClick = { onSelectedCategory(it) }, modifier = Modifier
                    .wrapContentHeight()
                    .padding(horizontal = 4.dp)
            )
        }
    }
}

@PreviewForBothTheme
@Composable
private fun ExpenseCategoriesPreview() {
    ProexpenseTheme {
        Surface {
            ExpenseCategoriesView(
                categoryList = ExpenseLogCategory.entries,
                selectedCategory = ExpenseLogCategory.TRANSPORT, onSelectedCategory = {})
        }
    }
}