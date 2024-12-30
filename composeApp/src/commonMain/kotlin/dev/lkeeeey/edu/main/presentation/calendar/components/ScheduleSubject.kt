package dev.lkeeeey.edu.main.presentation.calendar.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.lkeeeey.edu.core.presentation.Theme
import dev.lkeeeey.edu.main.domain.models.TimeTableModel
import ecucateme.composeapp.generated.resources.Res
import ecucateme.composeapp.generated.resources.Thin
import org.jetbrains.compose.resources.Font

@Composable
fun ScheduleSubject (
    subject: TimeTableModel
) {

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "${subject.start} - ${subject.end}",
            style = TextStyle(
                fontSize = 10.sp,
                fontFamily = FontFamily(Font(Res.font.Thin)),
                fontWeight = FontWeight(400),
                color = Theme.colors.blackProfile,
                letterSpacing = 0.3.sp,
            )
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = subject.name.name,
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(Res.font.Thin)),
                fontWeight = FontWeight(400),
                color = Theme.colors.blackProfile,
                letterSpacing = 0.3.sp,
            )
        )
    }

}
