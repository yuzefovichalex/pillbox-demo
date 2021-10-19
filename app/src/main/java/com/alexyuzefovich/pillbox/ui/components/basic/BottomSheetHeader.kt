package com.alexyuzefovich.pillbox.ui.components.basic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun BottomSheetHeader(
    title: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.zIndex(1f)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(color = MaterialTheme.colors.background)
                .then(modifier),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = title,
                modifier = Modifier.padding(horizontal = 16.dp),
                style = MaterialTheme.typography.subtitle1
            )
        }

        Divider(thickness = 0.5.dp)
    }
}