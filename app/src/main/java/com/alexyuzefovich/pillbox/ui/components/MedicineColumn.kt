package com.alexyuzefovich.pillbox.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.alexyuzefovich.pillbox.R
import com.alexyuzefovich.pillbox.ui.components.basic.BottomSheetHeader
import com.alexyuzefovich.pillbox.ui.model.Medicine
import com.alexyuzefovich.pillbox.ui.theme.LightGrey
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import kotlinx.coroutines.flow.StateFlow

@ExperimentalMaterialApi
@Composable
fun MedicineColumn(
    medicineFlow: StateFlow<List<Medicine>>,
    onMedicineCreate: () -> Unit,
    onMedicineDetails: (Medicine) -> Unit
) {
    val systemInsets = rememberInsetsPaddingValues(insets = LocalWindowInsets.current.systemBars)

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = {
                    Text(
                        text = stringResource(id = R.string.new_pill),
                        style = MaterialTheme.typography.button
                    )
                },
                onClick = { onMedicineCreate() },
                modifier = Modifier.padding(bottom = systemInsets.calculateBottomPadding()),
                icon = {
                    Image(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = stringResource(id = R.string.new_pill)
                    )
                },
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(color = LightGrey)
            ) {
                BottomSheetHeader(title = stringResource(id = R.string.your_pills))

                val pills by medicineFlow.collectAsState()

                LazyColumn(
                    contentPadding = PaddingValues(
                        start = 8.dp,
                        top = 16.dp,
                        end = 8.dp,
                        bottom = 16.dp + systemInsets.calculateBottomPadding()
                    ),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(pills) { pill ->
                        MedicineCard(pill, onMedicineDetails)
                    }
                }
            }
        }
    )
}