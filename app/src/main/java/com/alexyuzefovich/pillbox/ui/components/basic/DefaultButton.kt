package com.alexyuzefovich.pillbox.ui.components.basic

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DefaultButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 56.dp, vertical = 12.dp)
    ) {
        Text(
            text = text.uppercase(),
            style = MaterialTheme.typography.button
        )
    }
}