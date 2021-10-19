package com.alexyuzefovich.pillbox.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Circle
import androidx.compose.material.icons.rounded.Notes
import androidx.compose.material.icons.rounded.Today
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.alexyuzefovich.pillbox.R
import com.alexyuzefovich.pillbox.ui.components.basic.RippledCard
import com.alexyuzefovich.pillbox.ui.model.Medicine
import com.alexyuzefovich.pillbox.ui.model.Type
import com.alexyuzefovich.pillbox.ui.theme.*

@ExperimentalMaterialApi
@Composable
fun MedicineCard(
    medicine: Medicine,
    onMedicineDetails: (Medicine) -> Unit
) {
    RippledCard(
        modifier = Modifier.fillMaxWidth(),
        shape = Shapes.large,
        elevation = 0.dp,
        onClick = {
            onMedicineDetails(medicine)
        }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                MedicineTypeIndicator(medicine.type)

                Column(
                    modifier = Modifier.padding(start = 16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = medicine.name,
                        style = MaterialTheme.typography.h4
                    )

                    MedicinePrimaryParametersRow(medicine)
                }
            }

            MedicineDescriptionRow(
                text = "До 14 октября 2022",
                textStyle = MaterialTheme.typography.body1,
                textColor = MaterialTheme.colors.onBackground,
                icon = Icons.Rounded.Today,
                iconColor = SecondaryGrey,
                modifier = Modifier.padding(top = 16.dp)
            )

            if (!medicine.notes.isNullOrBlank()) {
                MedicineDescriptionRow(
                    text = medicine.notes,
                    textStyle = MaterialTheme.typography.body2,
                    textColor = SecondaryGrey,
                    icon = Icons.Rounded.Notes,
                    iconColor = SecondaryGrey,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

@Composable
private fun MedicineTypeIndicator(type: Type) {
    Box(
        modifier = Modifier
            .width(56.dp)
            .height(56.dp)
            .background(
                color = Green200,
                shape = Shapes.large
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = type.imageResId),
            contentDescription = null,
            modifier = Modifier
                .width(32.dp)
                .height(32.dp),
            colorFilter = ColorFilter.tint(Green700)
        )
    }
}

@Composable
private fun MedicinePrimaryParametersRow(medicine: Medicine) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(
                R.string.quantitative_element,
                medicine.quantity,
                medicine.quantityMetric.simpleName
            ),
            modifier = Modifier.padding(end = 4.dp),
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.primaryVariant
        )

        if (medicine.dosage != null) {
            Image(
                imageVector = Icons.Rounded.Circle,
                contentDescription = null,
                modifier = Modifier
                    .width(4.dp)
                    .height(4.dp)
            )

            Text(
                text = stringResource(
                    R.string.quantitative_element,
                    medicine.dosage,
                    medicine.dosageMetric.simpleName
                ),
                modifier = Modifier.padding(start = 4.dp),
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.primaryVariant
            )
        }
    }
}

@Composable
private fun MedicineDescriptionRow(
    text: String,
    textStyle: TextStyle,
    textColor: Color,
    icon: ImageVector,
    iconColor: Color,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp)
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .width(32.dp)
                .height(32.dp)
                .background(
                    color = LightGrey,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Image(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier
                    .width(16.dp)
                    .height(16.dp),
                colorFilter = ColorFilter.tint(iconColor)
            )
        }

        Text(
            text = text,
            modifier = Modifier
                .padding(horizontal = 16.dp),
            style = textStyle,
            color = textColor
        )
    }
}