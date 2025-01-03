package dev.lkeeeey.edu.library.presentation.teachers

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.lkeeeey.edu.library.presentation.teachers.components.TeacherCard
import dev.lkeeeey.edu.library.presentation.teachers.viewmodel.AllTeachersAction
import dev.lkeeeey.edu.library.presentation.teachers.viewmodel.AllTeachersEvent
import dev.lkeeeey.edu.library.presentation.teachers.viewmodel.AllTeachersState

@Composable
fun AllTeachersView (
    state: AllTeachersState,
    onEvent: (AllTeachersEvent) -> Unit,
    onOpen: (AllTeachersAction) -> Unit,
) {

    Column (
        modifier = Modifier
            .animateContentSize()
            .fillMaxSize()
//            .padding(16.dp)
    ) {

//        Spacer(modifier = Modifier.height(12.dp))
//
//        OutlinedText(
//            previousData = state.subject,
//            label = "Введите предмет",
//        ) {
//            onEvent(AllTeachersEvent.OnSubjectUpdate(it))
//        }

//        Spacer(Modifier.height(24.dp))

        Column (
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            state.teachers.forEach { teacher->
                TeacherCard(
                    teacher = teacher
                ) {
                    onEvent(AllTeachersEvent.OnOpenTeacherDescription(username = teacher.username))
//                    onOpenTeacherDescription()
                    onOpen(AllTeachersAction.OnOpenTeacherDescription)
                }
            }
        }
    }
}
