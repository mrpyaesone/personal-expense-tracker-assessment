package com.example.pro_expense.ui.expenselist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.pro_expense.R
import com.example.pro_expense.ui.theme.PreviewForBothTheme
import com.example.pro_expense.ui.theme.ProexpenseTheme
import com.example.pro_expense.ui.theme.green
import com.example.pro_expense.ui.theme.red
import com.example.pro_expense.ui.vo.ExpenseLogVO

/**
 * Created by pyae on 15/7/26
 */

@Composable
fun ExpenseLogItemView(
    item: ExpenseLogVO, onItemClick: (ExpenseLogVO) -> Unit,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(8.dp)
    ) {
        IconButton(
            onClick = {}, modifier = Modifier
                .clip(CircleShape)
                .background(colorScheme.primary)
                .size(44.dp)
        ) {
            Icon(
                painter = painterResource(item.icon),
                contentDescription = "Back", tint = colorScheme.surface,
                modifier = Modifier.size(28.dp)
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp)
        ) {
            Text(
                item.title, style = typography.bodyLarge,
                maxLines = 1, overflow = TextOverflow.Ellipsis
            )

            Text(
                item.date, style = typography.bodyMedium,
                maxLines = 1, overflow = TextOverflow.Ellipsis
            )
        }

        Text(
            "$" + item.amount,
            style = typography.headlineSmall.copy(color = if (item.isExpense) red else green),
//            maxLines = 1, overflow = TextOverflow.Ellipsis
        )
    }
}

@PreviewForBothTheme
@Composable
private fun ExpenseLogItemViewPreview() {
    ProexpenseTheme {
        Surface {
            ExpenseLogItemView(
                item = ExpenseLogVO(
                    id = 0,
                    icon = R.drawable.ic_bill,
                    title = "title",
                    amount = "2",
                    date = "222",
                    isExpense = true
                ), onItemClick = {})
        }
    }
}