package com.alexyuzefovich.pillbox.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notes
import androidx.compose.material.icons.rounded.Title
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.alexyuzefovich.pillbox.R
import com.alexyuzefovich.pillbox.ui.components.basic.BottomSheetHeader
import com.alexyuzefovich.pillbox.ui.components.basic.DefaultButton
import com.alexyuzefovich.pillbox.ui.components.basic.DefaultTextField
import com.alexyuzefovich.pillbox.ui.components.basic.DropdownSelector
import com.alexyuzefovich.pillbox.ui.model.DosageMetric
import com.alexyuzefovich.pillbox.ui.model.MedicineState
import com.alexyuzefovich.pillbox.ui.model.QuantityMetric
import com.alexyuzefovich.pillbox.ui.model.Type
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues

@ExperimentalAnimationApi
@Composable
fun MedicineParametersSheet(
    medicineState: MedicineState,
    onSave: (MedicineState) -> Unit
) {
    val systemInsets = rememberInsetsPaddingValues(insets = LocalWindowInsets.current.systemBars)
    val imeInsets = rememberInsetsPaddingValues(insets = LocalWindowInsets.current.ime)

    Column {
        BottomSheetHeader(title = stringResource(id = R.string.medicine_parameters))

        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp
                )
                .verticalScroll(state = scrollState)
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            DefaultTextField(
                value = medicineState.type.simpleName.capitalize(Locale.current),
                onValueChange = {
                    medicineState.type = Type.getBySimpleName(it)
                },
                readOnly = true,
                icon = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_tablet),
                        contentDescription = stringResource(id = R.string.type_of_medicine),
                        modifier = Modifier.size(24.dp)
                    )
                },
                suffix = {
                    DropdownSelector(
                        currentValue = medicineState.type.simpleName.capitalize(Locale.current),
                        values = Type.simpleNames().map { it.capitalize(Locale.current) },
                        onValueSelected = {
                            medicineState.type = Type.getBySimpleName(it.lowercase())
                        },
                        showLabel = false
                    )
                },
                label = stringResource(id = R.string.type_of_medicine),
                placeholder = stringResource(id = R.string.type_of_medicine)
            )

            Spacer(modifier = Modifier.height(16.dp))

            DefaultTextField(
                value = medicineState.name,
                onValueChange = {
                    medicineState.name = it
                },
                icon = {
                    Image(
                        imageVector = Icons.Rounded.Title,
                        contentDescription = stringResource(id = R.string.name_of_medicine)
                    )
                },
                label = stringResource(id = R.string.name_of_medicine),
                placeholder = stringResource(id = R.string.name_of_medicine_hint),
                errorState = !medicineState.isNameValid
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row {
                Box(modifier = Modifier.padding(top = 40.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_tablet_plate),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }

                DefaultTextField(
                    value = medicineState.quantity.toString().takeIf { it > "0" }.orEmpty(),
                    onValueChange = {
                        medicineState.quantity = it.toIntOrNull() ?: 0
                    },
                    modifier = Modifier.weight(1.0f, true),
                    suffix = {
                        DropdownSelector(
                            currentValue = medicineState.quantityMetric.simpleName,
                            values = QuantityMetric.simpleNames(),
                            onValueSelected = {
                                medicineState.quantityMetric = QuantityMetric.getBySimpleName(it)
                            }
                        )
                    },
                    label = stringResource(id = R.string.quantity),
                    placeholder = stringResource(id = R.string.quantity_hint),
                    singleLine = true,
                    errorState = !medicineState.isQuantityValid
                )

                DefaultTextField(
                    value = medicineState.dosage.toString().takeIf { it > "0" }.orEmpty(),
                    onValueChange = { changedValue ->
                        if (changedValue.isEmpty()) {
                            medicineState.dosage = 0
                        } else {
                            changedValue.toIntOrNull()?.let {
                                medicineState.dosage = it
                            }
                        }
                    },
                    modifier = Modifier.weight(1.0f, true),
                    suffix = {
                        DropdownSelector(
                            currentValue = medicineState.dosageMetric.simpleName,
                            values = DosageMetric.simpleNames(),
                            onValueSelected = {
                                medicineState.dosageMetric = DosageMetric.getBySimpleName(it)
                            }
                        )
                    },
                    label = stringResource(id = R.string.dosage),
                    placeholder = stringResource(id = R.string.dosage_hint),
                    singleLine = true
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            DefaultTextField(
                value = medicineState.notes,
                onValueChange = {
                    medicineState.notes = it
                },
                icon = {
                    Image(
                        imageVector = Icons.Rounded.Notes,
                        contentDescription = stringResource(id = R.string.notes_about_medicine)
                    )
                },
                label = stringResource(id = R.string.notes_about_medicine),
                placeholder = stringResource(id = R.string.notes_about_medicine_hint)
            )

            Spacer(modifier = Modifier.height(16.dp))

            AnimatedVisibility(
                visible = !medicineState.isMedicineValid,
                modifier = Modifier.padding(horizontal = 24.dp)
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = MaterialTheme.typography.body2.toSpanStyle()
                        ) {
                            append(stringResource(id = R.string.invalid_medicine_warning_start))
                        }

                        withStyle(
                            style = MaterialTheme.typography.h6.copy(
                                color = MaterialTheme.colors.error
                            ).toSpanStyle()
                        ) {
                            append(stringResource(id = R.string.invalid_medicine_warning_highlighted))
                        }

                        withStyle(
                            style = MaterialTheme.typography.body2.toSpanStyle()
                        ) {
                            append(stringResource(id = R.string.invalid_medicine_warning_end))
                        }
                    },
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 16.dp,
                        end = 16.dp
                    ),
                contentAlignment = Alignment.CenterEnd
            ) {
                DefaultButton(
                    text = stringResource(id = R.string.save),
                    onClick = { onSave(medicineState) },
                    enabled = medicineState.isMedicineValid
                )
            }

            val bottomContentPadding = if (imeInsets.calculateBottomPadding() == 0.dp) {
                16.dp + systemInsets.calculateBottomPadding()
            } else {
                16.dp
            }

            Spacer(modifier = Modifier.height(bottomContentPadding))
        }
    }
}