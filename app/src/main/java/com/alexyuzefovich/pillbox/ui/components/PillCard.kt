package com.alexyuzefovich.pillbox.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
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
import com.alexyuzefovich.pillbox.ui.model.Pill
import com.alexyuzefovich.pillbox.ui.theme.LightGrey
import com.alexyuzefovich.pillbox.ui.theme.SecondaryGrey
import com.alexyuzefovich.pillbox.ui.theme.Shapes

@Composable
fun PillCard(pill: Pill) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = Shapes.large,
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                PillTypeIndicator(pill)

                Column(
                    modifier = Modifier.padding(start = 16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = pill.name,
                        style = MaterialTheme.typography.h5
                    )

                    PillPrimaryParametersRow(pill)
                }
            }

            PillDescriptionRow(
                text = "До 14 октября 2022",
                textStyle = MaterialTheme.typography.body1,
                textColor = MaterialTheme.colors.onBackground,
                icon = Icons.Rounded.Today,
                iconColor = SecondaryGrey,
                modifier = Modifier.padding(top = 16.dp)
            )

            if (pill.notes != null) {
                PillDescriptionRow(
                    text = pill.notes,
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
private fun PillTypeIndicator(pill: Pill) {
    Box(
        modifier = Modifier
            .width(56.dp)
            .height(56.dp)
            .background(
                color = MaterialTheme.colors.primary,
                shape = Shapes.large
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = pill.type.imageResId),
            contentDescription = null,
            modifier = Modifier
                .width(32.dp)
                .height(32.dp),
            colorFilter = ColorFilter.tint(MaterialTheme.colors.primaryVariant)
        )
    }
}

@Composable
private fun PillPrimaryParametersRow(pill: Pill) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.quantitative_element, pill.quantity, pill.quantityMetric),
            modifier = Modifier.padding(end = 4.dp),
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.primaryVariant
        )

        if (pill.dosage != null && pill.dosageMetric != null) {
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
                    pill.dosage,
                    pill.dosageMetric
                ),
                modifier = Modifier.padding(start = 4.dp),
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.primaryVariant
            )
        }
    }
}

@Composable
private fun PillDescriptionRow(
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