package com.alexyuzefovich.pillbox.ui.components.basic

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircleOutline
import androidx.compose.material.icons.rounded.ExpandMore
import androidx.compose.material.icons.rounded.RadioButtonUnchecked
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.alexyuzefovich.pillbox.R

@Composable
fun DropdownSelector(
    currentValue: String,
    values: List<String>,
    onValueSelected: (String) -> Unit,
    showLabel: Boolean = true
) {
    Row {
        var isDropdownMenuExpanded by remember { mutableStateOf(false) }

        TextButton(
            onClick = { isDropdownMenuExpanded = true }
        ) {
            if (showLabel) {
                Text(text = currentValue)
            }

            Icon(
                imageVector = Icons.Rounded.ExpandMore,
                contentDescription = stringResource(id = R.string.expand_more),
                modifier = Modifier.size(16.dp)
            )
        }

        DropdownMenu(
            expanded = isDropdownMenuExpanded,
            onDismissRequest = { isDropdownMenuExpanded = false }
        ) {
            values.forEach {
                DropdownMenuItem(
                    onClick = {
                        onValueSelected(it)
                        isDropdownMenuExpanded = false
                    }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val selectedValue = it == currentValue

                        val imageVector =
                            if (selectedValue) Icons.Rounded.CheckCircleOutline
                            else Icons.Rounded.RadioButtonUnchecked

                        val imageSize = if (selectedValue) 24.dp else 16.dp

                        val tint =
                            if (selectedValue) MaterialTheme.colors.primary
                            else MaterialTheme.colors.onSurface.copy(alpha = 0.68f)

                        val textColor =
                            if (selectedValue) MaterialTheme.colors.onSurface
                            else MaterialTheme.colors.onSurface.copy(alpha = 0.68f)

                        val textStyle =
                            if (selectedValue) MaterialTheme.typography.h5
                            else MaterialTheme.typography.body1

                        Icon(
                            imageVector = imageVector,
                            contentDescription = stringResource(id = R.string.selected),
                            modifier = Modifier.size(imageSize),
                            tint = tint
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = it,
                            color = textColor,
                            style = textStyle
                        )

                        Spacer(modifier = Modifier.width(32.dp))
                    }
                }
            }
        }
    }
}