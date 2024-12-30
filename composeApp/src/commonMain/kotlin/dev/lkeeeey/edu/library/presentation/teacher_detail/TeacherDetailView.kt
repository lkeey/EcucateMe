package dev.lkeeeey.edu.library.presentation.teacher_detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import dev.lkeeeey.edu.library.presentation.teachers.viewmodel.AllTeachersEvent
import dev.lkeeeey.edu.library.presentation.teachers.viewmodel.AllTeachersState

@Composable
fun TeacherDetailView (
    state: AllTeachersState,
    onEvent: (AllTeachersEvent) -> Unit,
) {

    Text(text = "teacher - ${state.selectedUsername}")
}
