package dev.lkeeeey.edu.main.presentation.calendar.components

import androidx.annotation.RequiresApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ecucateme.composeapp.generated.resources.Res
import ecucateme.composeapp.generated.resources.Thin
import org.jetbrains.compose.resources.Font

/*
* show current month with year
* */

@Composable
fun MonthText(
//    selectedMonth: YearMonth
) {

//    val monthWithYear = selectedMonth.month.getDisplayName(
//        TextStyle.FULL_STANDALONE,
//        context.resources.configuration.locales[0]
//    ) + " " + selectedMonth.year


    Text(
        text = "monthWithYear",
        style = TextStyle(
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(Res.font.Thin)),
            fontWeight = FontWeight(500),
            color = Color(0xFF222222),
            letterSpacing = 0.32.sp,
        )
    )
}
