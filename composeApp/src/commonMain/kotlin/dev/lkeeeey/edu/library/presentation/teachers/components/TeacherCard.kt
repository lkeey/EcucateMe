package dev.lkeeeey.edu.library.presentation.teachers.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.lkeeeey.edu.core.presentation.Theme
import dev.lkeeeey.edu.library.domain.models.TeacherModel
import ecucateme.composeapp.generated.resources.Bold
import ecucateme.composeapp.generated.resources.Res
import ecucateme.composeapp.generated.resources.Thin
import ecucateme.composeapp.generated.resources.ic_calendar_no_plans
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource

@Composable
fun TeacherCard (
    teacher: TeacherModel,
    onClick: (TeacherModel) -> Unit
) {
    Row  (
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .clip(
                RoundedCornerShape(12.dp)
            )
            .background(
                color = White,
                shape = RoundedCornerShape(
                    size = 8.dp
                )
            )
            .padding(16.dp)
            .clickable {
                onClick(teacher)
            },
        horizontalArrangement = Arrangement.Start
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            painter = painterResource(Res.drawable.ic_calendar_no_plans),
            contentDescription = "image of teacher",
            alignment = Alignment.Center
        )

        Column (
           modifier = Modifier
               .fillMaxWidth()
               .weight(1f)
        ) {
            Text(
                text = teacher.name,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(Res.font.Bold)),
                    fontWeight = FontWeight(400),
                    color = Theme.colors.blackProfile,
                    letterSpacing = 0.3.sp,
                )
            )

            Text(
                text = teacher.subject,
                style = TextStyle(
                    fontSize = 10.sp,
                    fontFamily = FontFamily(Font(Res.font.Thin)),
                    fontWeight = FontWeight(400),
                    color = Theme.colors.blackProfile,
                    letterSpacing = 0.3.sp,
                )
            )
        }
    }
}
