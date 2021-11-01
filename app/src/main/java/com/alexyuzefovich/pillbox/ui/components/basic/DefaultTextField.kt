package com.alexyuzefovich.pillbox.ui.components.basic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DefaultTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    readOnly: Boolean = false,
    icon: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    label: String? = null,
    placeholder: String? = null,
    singleLine: Boolean = false,
    errorState: Boolean = false
) {
    Row(
        modifier = modifier
    ) {
        if (icon != null) {
            Box(modifier = Modifier.padding(top = 40.dp)) {
                icon()
            }
        }

        Column(
            modifier = Modifier.padding(start = 16.dp)
        ) {
            if (label != null) {
                val labelColor = if (errorState) MaterialTheme.colors.error else MaterialTheme.colors.primary

                Text(
                    text = label,
                    modifier = Modifier.padding(start = 8.dp, bottom = 4.dp),
                    color = labelColor,
                    style = MaterialTheme.typography.h6
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f),
                        shape = MaterialTheme.shapes.large
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = value,
                    onValueChange = onValueChange,
                    modifier = Modifier.weight(1.0f, true),
                    readOnly = readOnly,
                    placeholder = {
                        if (!placeholder.isNullOrBlank()) {
                            Text(
                                text = placeholder,
                                style = MaterialTheme.typography.body1
                            )
                        }
                    },
                    singleLine = singleLine,
                    shape = RoundedCornerShape(16.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    )
                )

                if (suffix != null) {
                    suffix()

                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }

    }
}