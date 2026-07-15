package com.example.pro_expense.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.pro_expense.ui.theme.PreviewForBothTheme
import com.example.pro_expense.ui.theme.ProexpenseTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    title: String = "", titleColor: Color = Color.Unspecified,
    canNavBack: Boolean = false,
    navigateUp: @Composable () -> Unit = {},
    color: Color = MaterialTheme.colorScheme.surface,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title, maxLines = 1, overflow = TextOverflow.Ellipsis,
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = color,
            scrolledContainerColor = Color.Unspecified,
            navigationIconContentColor = Color.Unspecified,
            titleContentColor = titleColor,
            actionIconContentColor = Color.Unspecified
        ),
        modifier = modifier.fillMaxWidth(),
        navigationIcon = { if (canNavBack) navigateUp() }
    )
}

@PreviewForBothTheme
@Composable
private fun AppBarPreview() {
    ProexpenseTheme {
        AppBar(title = "title", canNavBack = true, navigateUp = {
            IconButton(
                onClick = {}, modifier = Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .size(36.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = "Back", tint = MaterialTheme.colorScheme.surface
                )
            }
        })
    }
}