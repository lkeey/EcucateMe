package dev.lkeeeey.edu.main.presentation.profile.timetable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.lkeeeey.edu.core.presentation.Theme
import dev.lkeeeey.edu.main.presentation.profile.timetable.components.ReadOnlyDropDown
import dev.lkeeeey.edu.main.presentation.profile.timetable.viewmodel.TimeTableEvent
import dev.lkeeeey.edu.main.presentation.profile.timetable.viewmodel.TimeTableState
import ecucateme.composeapp.generated.resources.Bold
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
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            var nameDay = ""
            when (state.dayIndex) {
                0 -> nameDay = "Понедельник"
                1 -> nameDay = "Вторник"
                2 -> nameDay = "Среда"
                3 -> nameDay = "Четверг"
                4 -> nameDay = "Пятница"
                5 -> nameDay = "Суббота"
                6 -> nameDay = "Воскресенье"
            }

            IconButton(
                modifier = Modifier
                    .rotate(90f),
                enabled = state.dayIndex > 0,
                onClick = {
                    onEvent(TimeTableEvent.OnChangeDay(state.dayIndex-1))
                }
            ) {
                Icon(
                    Icons.Filled.ArrowDropDown,
                    "contentDescription",
                    tint = if (state.dayIndex > 0) Theme.colors.primaryBackground.copy(1f) else Theme.colors.secondaryBorder.copy(1f)
                )
            }

            Text(
                text = nameDay,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(Res.font.Bold)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF222222),
                    letterSpacing = 0.32.sp,
                )
            )

            IconButton(
                modifier = Modifier
                    .rotate(270f),
                enabled = state.dayIndex < 6,
                onClick = {
                    onEvent(TimeTableEvent.OnChangeDay(state.dayIndex+1))
                }
            ) {
                Icon(
                    Icons.Filled.ArrowDropDown,
                    "contentDescription",
                    tint = if (state.dayIndex < 6) Theme.colors.primaryBackground.copy(1f) else Theme.colors.secondaryBorder.copy(1f)
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
                .weight(weight = 1f, fill = false)
                .clip(
                    RoundedCornerShape(12.dp)
                )
                .background(
                    color = White,
                    shape = RoundedCornerShape(
                        size = 8.dp
                    )
                )
        ) {

            if(state.savedSubjects.isNotEmpty()) {
                state.savedSubjects.forEachIndexed { index, subjectSchedule->
//                    EditSubject(
//                        index = index,
//                        subject = subject,
//                    ) { index, subject->
//                        onEvent(TimeTableEvent.OnSubjectUpdate(index = index, subject = subject))
//                    }

                    ReadOnlyDropDown(
                        options = state.subjectIds,
                        previousData = subjectSchedule.name.name,
                        label = "${index+1}-й предмет"
                    ) { subj ->

//                        id previous - to delete
//                        start and end time, weekday, subjectID - to create

                        onEvent(TimeTableEvent.OnSubjectUpdate(
                            deletedSubject = subjectSchedule.id,
                            subjectNew = subj,
                            subjectNum = index
                        ))
                    }
                }
            } else {
                Text(
                    text = "Уроков нет!",
                    modifier = Modifier
                        .fillMaxSize(),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(Res.font.Thin)),
                        fontWeight = FontWeight(400),
                        color = Theme.colors.secondaryBorder,
                        letterSpacing = 0.3.sp,
                    ),
                    textAlign = TextAlign.Center,

                )
            }


            Spacer(modifier = Modifier.weight(.1f))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                shape = RoundedCornerShape(size = 32.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Theme.colors.primaryBackground.copy(alpha = 1f),
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

        Spacer(modifier = Modifier.weight(.1f))

//        Button(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 32.dp, vertical = 8.dp),
//            shape = RoundedCornerShape(size = 16.dp),
//            colors = ButtonDefaults.buttonColors(
//                backgroundColor = Theme.colors.primaryBackground.copy(alpha = 1f),
//            ),
//            onClick = {
//                onEvent(TimeTableEvent.OnSaveDay)
//            }
//        ) {
//            Text(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 4.dp),
//                text = "Сохранить",
//                style = TextStyle(
//                    fontSize = 20.sp,
//                    fontFamily = FontFamily(Font(Res.font.Thin)),
//                    fontWeight = FontWeight(600),
//                    color = White,
//                    textAlign = TextAlign.Center
//                )
//            )
//        }
    }
}
