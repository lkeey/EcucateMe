package dev.lkeeeey.edu.main.presentation.profile.timetable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.lkeeeey.edu.core.presentation.Theme
import dev.lkeeeey.edu.main.presentation.profile.timetable.components.EditSubject
import dev.lkeeeey.edu.main.presentation.profile.timetable.timetable.TimeTableEvent
import dev.lkeeeey.edu.main.presentation.profile.timetable.timetable.TimeTableState
import ecucateme.composeapp.generated.resources.Res
import ecucateme.composeapp.generated.resources.Thin
import org.jetbrains.compose.resources.Font

@Composable
fun TimeTableView  (
    state: TimeTableState,
    onEvent: (TimeTableEvent) -> Unit,
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = state.dayIndex.toString(),
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(Res.font.Thin)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF222222),
                    letterSpacing = 0.32.sp,
                )
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 100.dp)
                .padding(16.dp)
                .clip(
                    RoundedCornerShape(12.dp)
                )
                .background(
                    color = White,
                    shape = RoundedCornerShape(
                        size = 8.dp
                    )
                )
                .verticalScroll(rememberScrollState())
                .weight(weight = .5f, fill = false)
        ) {
            state.subjects.forEachIndexed { index, subject->
                EditSubject(
                    index = index,
                    subject = subject,
                ) { index, subject->
                    onEvent(TimeTableEvent.OnSubjectUpdate(index = index, subject = subject))
                }
            }
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            shape = RoundedCornerShape(size = 16.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Theme.colors.primaryBackground.copy(alpha = 1f),
                disabledBackgroundColor = Theme.colors.primaryBackground.copy(alpha = 0.3f)
            ),
            onClick = {
                onEvent(TimeTableEvent.OnAddLesson)
            }
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                text = "Еще урок",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(Res.font.Thin)),
                    fontWeight = FontWeight(600),
                    color = White,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}
