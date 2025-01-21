package dev.lkeeeey.edu.main.presentation.profile.subjects.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FilterChip
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.lkeeeey.edu.core.presentation.Theme
import ecucateme.composeapp.generated.resources.Res
import ecucateme.composeapp.generated.resources.Thin

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SubjectComponent (
    subject: String,
    previouslySelected: Boolean,
    onClick: (String) -> Unit
) {

    var isSelected by remember {
        mutableStateOf(previouslySelected)
    }

    FilterChip(
        onClick = {
            isSelected = !isSelected
            onClick(subject)
        },
        content = {
            Text(
                text = subject,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = FontFamily(org.jetbrains.compose.resources.Font(Res.font.Thin)),
                    fontWeight = FontWeight(500),
                    letterSpacing = 0.24.sp,
                )
            )
        },
        selected = isSelected,
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(
            width = 1.dp,
            color = when {
                isSelected -> Theme.colors.primaryBackground.copy(1f)
                else -> Theme.colors.greyDescription
            }
        ),
        colors = ChipDefaults.filterChipColors(
            backgroundColor = White,
            contentColor = Theme.colors.primaryBackground.copy(1f),

            selectedBackgroundColor = Theme.colors.primaryBackground.copy(1f),
            selectedContentColor = White,
        ),
    )
}
