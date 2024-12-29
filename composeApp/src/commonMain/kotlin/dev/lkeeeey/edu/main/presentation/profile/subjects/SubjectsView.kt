package dev.lkeeeey.edu.main.presentation.profile.subjects

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.lkeeeey.edu.core.presentation.Theme
import dev.lkeeeey.edu.core.presentation.components.btn.FilledBtn
import dev.lkeeeey.edu.core.presentation.components.fields.OutlinedText
import dev.lkeeeey.edu.main.presentation.profile.subjects.components.SubjectComponent
import dev.lkeeeey.edu.main.presentation.profile.subjects.viewmodel.SubjectsEvent
import dev.lkeeeey.edu.main.presentation.profile.subjects.viewmodel.SubjectsState
import ecucateme.composeapp.generated.resources.Res
import ecucateme.composeapp.generated.resources.Thin
import org.jetbrains.compose.resources.Font

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SubjectsView (
    state: SubjectsState,
    onEvent: (SubjectsEvent) -> Unit
) {

    Column (
        modifier = Modifier
            .fillMaxSize()
    ) {
        OutlinedText(
            previousData = state.subjectName,
            label = "Введите новый предмет",
        ) {
            onEvent(SubjectsEvent.OnUpdateSubjectName(it))
        }

        Spacer(Modifier.height(8.dp))

        FilledBtn(
            text = "Добавить"
        ) {
            onEvent(SubjectsEvent.OnCreateSubject)
        }

        Spacer(Modifier.height(16.dp))

        Text(
            text = "Нажмите на приоритетные предметы",
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(Res.font.Thin)),
                fontWeight = FontWeight(400),
                color = Theme.colors.blackProfile,
                textAlign = TextAlign.Center
            )
        )

        if (state.subjects.isNotEmpty()) {
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                maxItemsInEachRow = 4
            ) {
                state.subjects.forEach {
                    SubjectComponent(
                        subject = it.name,
                        previouslySelected = it.priority,
                        onClick = { subject->
                            onEvent(SubjectsEvent.OnUpdatePriority(!it.priority, it.id))
                        }
                    )
                }
            }
        }
    }

}
